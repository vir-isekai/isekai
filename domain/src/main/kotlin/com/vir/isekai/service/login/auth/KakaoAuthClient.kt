package com.vir.isekai.service.login.auth

import com.vir.isekai.dto.MemberSaveDTO
import com.vir.isekai.port.AuthPort
import mu.KotlinLogging
import org.springframework.stereotype.Service

private val log = KotlinLogging.logger {}

@Service
class KakaoAuthClient(
	private val authPort: AuthPort,
) : AuthClient {
	override fun getMemberSaveDTO(code: String): MemberSaveDTO {
// 		val accessToken = authPort.getAccessToken(code)
		val snsMemberInfo = authPort.getMemberSaveDTO(code)

		return snsMemberInfo
	}
}