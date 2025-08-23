package com.vir.isekai.domain.entity.enums.member

import com.vir.isekai.domain.entity.enums.Describable

enum class MemberRole(
	override val description: String,
) : Describable {
	NORMAL("일반 회원"),
	ADMIN("운영자"),
}