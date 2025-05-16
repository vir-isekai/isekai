package com.vir.isekai.port

import com.vir.isekai.dto.response.MemberResponse

interface AuthPort {
	fun getAccessToken(code: String): String

	fun getMemberSaveDTO(token: String): MemberResponse.Save
}