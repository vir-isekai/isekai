package com.vir.isekai.domain.dto.response

data class HomeResponse(
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
		val vtuberInfo: VtuberInfo,
		val agencyInfo: AgencyInfo,
	)

	data class VtuberInfo(
		val id: Long,
		val name: String,
		val profileImageUrl: String,
	)

	data class AgencyInfo(
		val id: Long,
		val name: String,
	)
}