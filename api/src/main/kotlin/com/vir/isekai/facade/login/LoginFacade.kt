package com.vir.isekai.facade.login

import com.vir.isekai.service.login.auth.AuthService
import org.springframework.stereotype.Component

@Component
class LoginFacade(
	private val authService: AuthService,
) {
	/**
	 * 추후 클라이언트에서 Token 받아오게 변경
	 *
	 * 서버에선 토큰 통해 해당 유저 정보만 처리
	 *
	 * 로그인 -> 기존 유저 존재 여부 확인 -> 회원가입 or 로그인 진행
	 */
	fun joinMemberOrLogin(code: String): Any {
		val snsMemberInfo = authService.getSNSMemberInfo(code)

		TODO()
	}
}