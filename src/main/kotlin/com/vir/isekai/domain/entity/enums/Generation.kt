package com.vir.isekai.domain.entity.enums

enum class Generation(
	override val description: String,
) : Describable {
	NONE("기수 없음"),
	FIRST("1기생"),
	MYSTIC("1기생"),
	UNIVERSE("2기생"),
	CLICHE("3기생"),
}