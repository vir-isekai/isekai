package com.vir.isekai.entity.enums.member

import com.vir.isekai.entity.enums.Describable

enum class MemberStatus(
	override val description: String,
) : Describable {
	ACTIVE("활성"),
	WITHDRAWN("비활성"),
}