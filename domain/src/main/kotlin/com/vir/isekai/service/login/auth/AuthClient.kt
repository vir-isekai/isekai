package com.vir.isekai.service.login.auth

import com.vir.isekai.dto.MemberDTO

interface AuthClient {
	fun getMemberSaveDTO(code: String): MemberDTO.Save
}