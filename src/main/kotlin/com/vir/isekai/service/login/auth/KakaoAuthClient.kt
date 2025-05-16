package com.vir.isekai.service.login.auth

import com.vir.isekai.dto.response.MemberResponse
import com.vir.isekai.port.AuthPort
import mu.KotlinLogging
import org.springframework.stereotype.Service

private val log = KotlinLogging.logger {}

@Service
class KakaoAuthClient(
	private val authPort: AuthPort,
) : AuthClient {
	override fun getMemberSaveDTO(code: String): MemberResponse.Save {
		val accessToken = authPort.getAccessToken(code)

		log.info { "Kakao Access token: $accessToken" }

		return authPort.getMemberSaveDTO(accessToken)
	}
}