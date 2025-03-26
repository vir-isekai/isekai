package com.vir.isekai.port

import com.vir.isekai.dto.MemberDTO

interface AuthPort {
	fun getAccessToken(code: String): String

	fun getSNSMemberInfo(token: String): MemberDTO.Save
}