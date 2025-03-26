package com.vir.isekai.port

import com.vir.isekai.dto.MemberDTO

interface AuthPort {
	fun getAccessToken(code: String): String

	fun getMemberSaveDTO(token: String): MemberDTO.Save
}