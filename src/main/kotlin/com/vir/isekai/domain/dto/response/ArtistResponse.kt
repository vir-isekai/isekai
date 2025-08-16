package com.vir.isekai.domain.dto.response

import com.vir.isekai.domain.dto.response.AgencyResponse.ChannelInfo
import java.time.LocalDate

class ArtistResponse {
	data class Detail(
		val name: String,
		val profileImageUrl: String,
		val age: Int,
		val height: Int,
		var race: String,
		val debutDate: LocalDate?,
		val graduateDate: LocalDate?,
		val agencyInfo: AgencyInfo?,
		val fandom: FandomInfo?,
		val channelInfos: List<ChannelInfo>,
	)

	data class FandomInfo(
		val fandomId: Long?,
		val name: String?,
	)

	data class AgencyInfo(
		val agencyId: Long,
		val name: String,
		val logoImageUrl: String,
	)
}