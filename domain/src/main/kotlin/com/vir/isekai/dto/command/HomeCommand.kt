package com.vir.isekai.dto.command

class HomeCommand(
	val countInfo: CountInfo,
	val popularVtuberInfos: List<PopularVtuberInfo>,
) {
	data class CountInfo(
		val agency: Long,
		val vtuber: Long,
		val member: Long,
		val visitor: Long,
	)

	data class PopularVtuberInfo(
		val vtuberId: Long,
		val vtuberName: String,
		val profileImageUrl: String,
		val agencyId: Long,
		val agencyName: String,
	)
}