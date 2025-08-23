package com.vir.isekai.domain.dto.response

import com.vir.isekai.domain.entity.enums.ChannelType
import java.time.LocalDate

class ArtistResponse {
	data class Detail(
		val name: String,
		val profileImageUrl: String,
		val debutDate: LocalDate?,
		val agencyInfo: AgencyInfo?,
		val channelInfos: List<ChannelInfo>,
	)

	data class AgencyInfo(
		val agencyId: Long,
		val name: String,
		val logoImageUrl: String,
	)

	data class ChannelInfo(
		val type: ChannelType,
		val url: String,
	)
}