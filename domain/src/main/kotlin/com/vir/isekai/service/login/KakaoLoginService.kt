package com.vir.isekai.service.login

import com.vir.isekai.port.OAuthPort
import org.springframework.stereotype.Service

@Service
class KakaoLoginService(
	private val oAuthPort: OAuthPort,
) {
	fun getKakaoAccessToken(code: String) {
	}
}