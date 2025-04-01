package com.vir.isekai.port

import com.vir.isekai.dto.MemberSaveDTO

interface AuthPort {
	fun getAccessToken(code: String): String

	fun getMemberSaveDTO(token: String): MemberSaveDTO
}