package com.vir.isekai.domain.entity.enums

enum class MediaType(
	override val description: String,
) : Describable {
	IMAGE("이미지"),
	VIDEO("비디오"),
}