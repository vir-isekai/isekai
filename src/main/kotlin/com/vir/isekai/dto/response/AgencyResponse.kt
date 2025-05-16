package com.vir.isekai.dto.response

import com.vir.isekai.entity.enums.ChannelType
import com.vir.isekai.entity.enums.Nation
import java.time.LocalDate

class AgencyResponse {
	data class Entry(
		val agencyId: Long,
		val name: String,
		val logoImageUrl: String,
	)

	data class Detail(
		val agencyId: Long,
		val name: String,
		val logoImageUrl: String,
		val nation: Nation,
		val establishedDate: LocalDate,
		val closedDate: LocalDate?,
		val vtuberInfos: List<VtuberInfo>,
		val fandomInfo: FandomInfo?,
		val channelInfos: List<ChannelInfo>,
	) {
		constructor(
			entry: Entry,
			vtuberInfos: List<VtuberInfo>,
			fandomInfo: FandomInfo?,
			channelInfos: List<ChannelInfo>
		) : this(
			entry.agencyId,
			entry.name,
			entry.logoImageUrl,
			entry.
		)
	}

	data class VtuberInfo(
		val vtuberId: Long,
		val name: String,
		val logoImageUrl: String,
	)

	data class FandomInfo(
		val fandomId: Long,
		val name: String,
		val logoImageUrl: String,
	)

	data class ChannelInfo(
		val type: ChannelType,
		val url: String,
	)
}