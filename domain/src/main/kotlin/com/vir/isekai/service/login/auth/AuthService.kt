package com.vir.isekai.service.login.auth

import com.vir.isekai.dto.MemberDTO

interface AuthService {
	fun getSNSMemberInfo(code: String): MemberDTO.Save
}