package com.vir.isekai.dto.request

class MemberRequest {
	data class Save(
		val snsId: String,
		val nickname: String,
	)
}