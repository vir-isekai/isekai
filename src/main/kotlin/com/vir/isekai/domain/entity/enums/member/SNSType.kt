package com.vir.isekai.domain.entity.enums.member

import com.vir.isekai.domain.entity.enums.Describable

enum class SNSType(
	override val description: String,
) : Describable {
	KAKAO("카카오톡"),
	GOOGLE("구글"),
	NAVER("네이버"),
	APPLE("애플"),
}