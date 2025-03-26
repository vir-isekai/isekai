package com.vir.isekai.service.login.auth

import com.vir.isekai.dto.MemberDTO
import com.vir.isekai.port.AuthPort
import mu.KotlinLogging
import org.springframework.stereotype.Service

private val log = KotlinLogging.logger {}

@Service
class KakaoAuthService(
	private val authPort: AuthPort,
) : AuthService {
	override fun getSNSMemberInfo(code: String): MemberDTO.Save {
		val accessToken = authPort.getAccessToken(code)
		val snsMemberInfo = authPort.getSNSMemberInfo(accessToken)

		return snsMemberInfo
	}
}