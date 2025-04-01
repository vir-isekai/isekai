package com.vir.isekai.service.login.auth

import com.vir.isekai.dto.MemberSaveDTO

interface AuthClient {
	fun getMemberSaveDTO(code: String): MemberSaveDTO
}