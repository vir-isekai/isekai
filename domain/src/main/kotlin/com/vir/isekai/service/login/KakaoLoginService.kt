package com.vir.isekai.service.login

import com.vir.isekai.port.OAuthPort
import mu.KotlinLogging
import org.springframework.stereotype.Service

private val log = KotlinLogging.logger {}

@Service
class KakaoLoginService(
	private val oAuthPort: OAuthPort,
) {
	fun getSNSMemberInfo(code: String): Any {
		val accessToken = oAuthPort.getAccessToken(code)

		val snsMemberInfo = oAuthPort.getSNSMemberInfo(accessToken)

		return snsMemberInfo
	}
}