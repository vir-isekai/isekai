package com.vir.isekai.dto.response

import com.vir.isekai.entity.enums.SNSType

class MemberResponse {
	data class Save(
		val snsId: String,
		val nickname: String,
		val snsType: SNSType,
	)
}