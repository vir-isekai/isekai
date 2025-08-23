package com.vir.isekai.domain.entity.enums.member

import com.vir.isekai.domain.entity.enums.Describable

enum class BusinessType(
	override val description: String,
) : Describable {
	AGENCY("소속사"),
	ARTIST("아티스트"),
}