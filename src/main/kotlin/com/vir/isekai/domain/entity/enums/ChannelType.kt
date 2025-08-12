package com.vir.isekai.domain.entity.enums

enum class ChannelType(
	override val description: String,
) : Describable {
	CHZZK("치지직"),
	TWITCH("트위치"),
	YOUTUBE("유튜브"),
	YOUTUBE_RE("유튜브 다시보기"),
	SOOP("숲"),
	NAVER("네이버 카페"),
}