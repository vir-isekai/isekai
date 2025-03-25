package com.vir.isekai.facade.login

import com.vir.isekai.service.login.KakaoLoginService
import org.springframework.stereotype.Component

@Component
class KakaoLoginFacade(
	private val kakaoLoginService: KakaoLoginService,
) {
	fun generateKakaoToken(code: String): String {
		return kakaoLoginService.getKakaoAccessToken(code)
	}
}