package com.vir.isekai.domain.dto.response

import com.vir.isekai.domain.entity.enums.SNSType

class MemberResponse {
	data class Save(
		val snsId: String,
		val nickname: String,
		val snsType: SNSType,
	)
}