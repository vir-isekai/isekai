package com.vir.isekai.service.login

import com.vir.isekai.port.OAuthPort
import mu.KotlinLogging
import org.springframework.stereotype.Service

private val log = KotlinLogging.logger {}

@Service
class KakaoLoginService(
	private val oAuthPort: OAuthPort,
) {
	fun getKakaoAccessToken(code: String): String {
		val accessToken = oAuthPort.getAccessToken(code)

		return accessToken
	}
}