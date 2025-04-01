package com.vir.isekai.entity.enums.member

import com.vir.isekai.entity.enums.Describable

enum class Gender(
	override val description: String,
) : Describable {
	MALE("남성"),
	FEMALE("여성"),
}