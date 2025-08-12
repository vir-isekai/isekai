package com.vir.isekai.domain.entity.enums

enum class SNSType(
	override val description: String,
) : Describable {
	KAKAO("카카오톡"),
	GOOGLE("구글"),
	APPLE("애플"),
}