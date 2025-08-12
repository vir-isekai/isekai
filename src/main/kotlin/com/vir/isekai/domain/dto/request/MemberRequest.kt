package com.vir.isekai.domain.dto.request

class MemberRequest {
	data class Save(
		val snsId: String,
		val nickname: String,
	)
}