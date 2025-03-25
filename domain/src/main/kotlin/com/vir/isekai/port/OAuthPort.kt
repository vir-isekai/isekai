package com.vir.isekai.port

interface OAuthPort {
	fun getAccessToken(code: String): String

	fun getSNSMemberInfo(token: String): Any
}