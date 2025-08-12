package com.vir.isekai.common.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
	private val jwtService: JwtService,
	private val userDetailsService: UserDetailsService,
) : OncePerRequestFilter() {
	override fun doFilterInternal(
		request: HttpServletRequest,
		response: HttpServletResponse,
		filterChain: FilterChain,
	) {
		val authHeader = request.getHeader("Authorization")
		
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response)
			return
		}
		
		val jwt = authHeader.substring(7)
		val username = jwtService.extractUsername(jwt)
		
		if (username != null && SecurityContextHolder.getContext().authentication == null) {
			try {
				val userDetails = userDetailsService.loadUserByUsername(username)
				
				if (jwtService.isValidToken(jwt, userDetails)) {
					val authToken =
						UsernamePasswordAuthenticationToken(
							userDetails,
							null,
							userDetails.authorities,
						)
					authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
					SecurityContextHolder.getContext().authentication = authToken
				}
			} catch (e: Exception) {
				// 사용자를 찾을 수 없거나 토큰이 유효하지 않은 경우 인증 실패
				// 로그만 기록하고 요청은 계속 진행
			}
		}
		
		filterChain.doFilter(request, response)
	}
}