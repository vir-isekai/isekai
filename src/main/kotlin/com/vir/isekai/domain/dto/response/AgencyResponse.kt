package com.vir.isekai.domain.dto.response

import com.vir.isekai.domain.entity.enums.ChannelType
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
		val establishedDate: LocalDate,
		val closedDate: LocalDate?,
		val artistInfos: List<ArtistInfo>,
		val channelInfos: List<ChannelInfo>,
	)

	data class ArtistInfo(
		val artistId: Long,
		val name: String,
		val logoImageUrl: String,
	)

	data class ChannelInfo(
		val type: ChannelType,
		val url: String,
	)
}