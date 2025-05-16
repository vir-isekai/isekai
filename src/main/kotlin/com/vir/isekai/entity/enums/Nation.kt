package com.vir.isekai.entity.enums

enum class Nation(
	override val description: String,
) : Describable {
	NONE("없음"),
	KOREA("대한민국"),
	JAPAN("일본"),
}