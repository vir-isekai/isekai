package com.vir.isekai.api.dto.response

class MemberRequest {
	data class Save(
		val snsId: String,
		val nickname: String,
	)
}