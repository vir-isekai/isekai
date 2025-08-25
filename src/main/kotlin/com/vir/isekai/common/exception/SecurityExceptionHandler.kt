package com.vir.isekai.common.exception

import com.vir.isekai.domain.dto.CommonResponse
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

private val log = KotlinLogging.logger {}

@RestControllerAdvice
class SecurityExceptionHandler {
	@ExceptionHandler(AccessDeniedException::class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	fun handleAccessDenied(ex: AccessDeniedException): CommonResponse<Nothing> {
		log.warn { "Access Denied: ${ex.message}" }
		return CommonResponse.error("접근 권한이 없습니다", HttpStatus.FORBIDDEN.value())
	}

	@ExceptionHandler(AuthenticationException::class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	fun handleAuthenticationException(ex: AuthenticationException): CommonResponse<Nothing> {
		log.warn { "Authentication Failed: ${ex.message}" }
		return CommonResponse.error("인증이 필요합니다", HttpStatus.UNAUTHORIZED.value())
	}
}