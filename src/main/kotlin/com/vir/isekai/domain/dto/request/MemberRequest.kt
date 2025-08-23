package com.vir.isekai.domain.dto.request

import com.vir.isekai.domain.entity.enums.member.BusinessType

class MemberRequest {
	data class Save(
		val snsId: String,
		val nickname: String,
	)

	data class Business(
		val memberId: Long,
		val businessType: BusinessType,
		val agencyId: Long?,
		val artistId: Long?,
	)
}