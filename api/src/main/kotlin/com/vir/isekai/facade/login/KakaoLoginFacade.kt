package com.vir.isekai.facade.login

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class KakaoLoginFacade {
	@Value("\${kakao.rest_api_key}")
	lateinit var apiKey: String

	@Value("\${kakao.redirect_uri}")
	lateinit var redirectURL: String

	fun generateKakaoToken(code: String) {
		TODO("Not yet implemented")
	}
}