package com.vir.isekai.entity.enums

enum class ChannelType(
	override val description: String,
) : Describable {
	CHZZK("치지직"),
	TWITCH("트위치"),
	YOUTUBE("유튜브"),
	SOOP("숲"),
	NAVER("네이버 카페"),
}