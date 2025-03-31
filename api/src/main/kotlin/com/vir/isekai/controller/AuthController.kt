package com.vir.isekai.controller

import com.vir.isekai.dto.AuthResponse
import com.vir.isekai.dto.CommonResponse
import com.vir.isekai.dto.TokenRefreshResponse
import com.vir.isekai.entity.enums.SNSType
import com.vir.isekai.facade.auth.AuthFacade
import com.vir.isekai.security.JwtService
import com.vir.isekai.security.UserPrincipal
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseCookie
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.Duration

@RestController
@RequestMapping("/api/auth")
class AuthController(
	private val authFacade: AuthFacade,
	private val jwtService: JwtService,
	private val userDetailsService: UserDetailsService,
) {
	@GetMapping("/login")
	fun callbackKakaoLogin(
		@RequestParam code: String,
		response: HttpServletResponse,
	): CommonResponse<AuthResponse> {
		val snsId = authFacade.joinMemberOrLogin(code) as String

		val userDetail = UserPrincipal.createFromSnsInfo(snsId, SNSType.KAKAO)

		val accessToken = jwtService.generateToken(userDetail)
		val refreshToken = jwtService.generateRefreshToken(userDetail)

		// 리프레시 토큰을 HTTP-Only 쿠키로 설정
		addRefreshTokenCookie(response, refreshToken)

		return CommonResponse.ok(AuthResponse(accessToken))
	}

	// 리프레시 토큰으로 새 액세스 토큰 발급
	@PostMapping("/refresh")
	fun refreshToken(
		request: HttpServletRequest,
		response: HttpServletResponse,
	): CommonResponse<TokenRefreshResponse> {
		// 쿠키에서 리프레시 토큰 추출
		val refreshToken =
			extractRefreshTokenFromCookie(request)
				?: return CommonResponse.error("리프레시 토큰이 없습니다")
		
		try {
			// 토큰에서 사용자명 추출
			val username =
				jwtService.extractUsername(refreshToken)
					?: return CommonResponse.error("유효하지 않은 토큰입니다")
				
			// 사용자 조회
			val userDetails = userDetailsService.loadUserByUsername(username)
			
			// 리프레시 토큰 검증
			if (!jwtService.isValidToken(refreshToken, userDetails)) {
				return CommonResponse.error("만료된 토큰입니다")
			}
			
			// 새 액세스 토큰 생성
			val newAccessToken = jwtService.generateToken(userDetails)
			
			return CommonResponse.ok(TokenRefreshResponse(newAccessToken))
		} catch (e: Exception) {
			return CommonResponse.error("토큰 갱신 중 오류가 발생했습니다")
		}
	}

	// 로그아웃 - 리프레시 토큰 쿠키 삭제
	@PostMapping("/logout")
	fun logout(response: HttpServletResponse): CommonResponse<Nothing> {
		val cookie =
			ResponseCookie.from("refresh_token", "")
				.maxAge(0)
				.httpOnly(true)
				.path("/api/auth/refresh")
				.build()
			
		response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString())
		
		return CommonResponse.noContent()
	}

	private fun addRefreshTokenCookie(
		response: HttpServletResponse,
		refreshToken: String,
	) {
		val cookie =
			ResponseCookie.from("refresh_token", refreshToken)
				.httpOnly(true)
				.secure(true) // HTTPS에서만 전송
				.path("/api/auth/refresh") // 필요한 경로에서만 접근 가능
				.maxAge(Duration.ofDays(7))
				.sameSite("Strict") // CSRF 방지
				.build()

		response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString())
	}

	private fun extractRefreshTokenFromCookie(request: HttpServletRequest): String? {
		val cookies = request.cookies ?: return null

		return cookies.find { it.name == "refresh_token" }?.value
	}
}