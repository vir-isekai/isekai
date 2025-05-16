package com.vir.isekai.entity.enums.member

import com.vir.isekai.entity.enums.Describable

enum class MemberRole(
	override val description: String,
) : Describable {
	NORMAL("일반 회원"),
	VTUBER("버튜버"),
	CEO("대표"),
	ILLUSTRATOR("일러스트레이터"),
	DEVELOPER("개발자"),
	ADMIN("운영자"),
}