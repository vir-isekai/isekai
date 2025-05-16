package com.vir.isekai.service.login.auth

import com.vir.isekai.dto.response.MemberResponse

interface AuthClient {
	fun getMemberSaveDTO(code: String): MemberResponse.Save
}