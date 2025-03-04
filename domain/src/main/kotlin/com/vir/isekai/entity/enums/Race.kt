package com.vir.isekai.entity.enums

enum class Race(
	override val description: String,
) : Describable {
	HUMAN("사람"),
	UNICORN("유니콘"),
	DRAGON("용"),
}