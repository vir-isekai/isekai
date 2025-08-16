package com.vir.isekai.domain.entity.enums.member

import com.vir.isekai.domain.entity.enums.Describable

enum class MemberRole(
	override val description: String,
) : Describable {
	NORMAL("일반 회원"),
	ARTIST("아티스트"),
	CEO("대표"),
	ILLUSTRATOR("일러스트레이터"),
	DEVELOPER("개발자"),
	ADMIN("운영자"),
}