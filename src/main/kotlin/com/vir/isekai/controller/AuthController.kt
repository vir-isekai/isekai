package com.vir.isekai.controller

import com.vir.isekai.common.security.JwtService
import com.vir.isekai.common.security.UserPrincipal
import com.vir.isekai.domain.dto.AuthResponse
import com.vir.isekai.domain.dto.CommonResponse
import com.vir.isekai.domain.dto.TokenRefreshResponse
import com.vir.isekai.domain.entity.enums.SNSType
import com.vir.isekai.facade.auth.AuthFacade
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import mu.KotlinLogging
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseCookie
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.*
import java.time.Duration

private val log = KotlinLogging.logger {}

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
		log.info { "code = $code" }
		
		val snsId = authFacade.joinMemberOrLogin(code)

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
				.secure(false)
				.path("/api/auth/refresh")
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