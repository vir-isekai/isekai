package com.vir.isekai.domain.dto.response

import java.time.LocalDate

class ArtistResponse {
	data class Detail(
		val name: String,
		val profileImageUrl: String,
		val debutDate: LocalDate?,
		val agencyInfo: AgencyResponse.Simple?,
		val channelInfos: List<ChannelResponse.Simple>,
	)
}