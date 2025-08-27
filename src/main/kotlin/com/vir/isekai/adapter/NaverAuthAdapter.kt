package com.vir.isekai.adapter

import com.vir.isekai.domain.dto.response.MemberResponse
import com.vir.isekai.port.AuthPort
import org.springframework.stereotype.Component

@Component
class NaverAuthAdapter : AuthPort {
	override fun getAccessToken(code: String): String {
		TODO("Not yet implemented")
	}

	override fun getMemberSaveDTO(token: String): MemberResponse.Save {
		TODO("Not yet implemented")
	}
}