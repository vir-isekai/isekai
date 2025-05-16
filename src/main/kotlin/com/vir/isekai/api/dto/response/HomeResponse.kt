package com.vir.isekai.dto.response

import com.vir.isekai.dto.command.HomeCommand

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

	companion object {
		fun from(command: HomeCommand): HomeResponse {
			val cc = command.countInfo
			val cpv = command.popularVtuberInfos

			return HomeResponse(
				CountInfo(
					cc.agency,
					cc.vtuber,
					cc.member,
					cc.visitor,
				),
				cpv.map {
					PopularVtuberInfo(
						VtuberInfo(
							it.vtuberId,
							it.vtuberName,
							it.profileImageUrl,
						),
						AgencyInfo(
							it.agencyId,
							it.agencyName,
						),
					)
				},
			)
		}
	}
}