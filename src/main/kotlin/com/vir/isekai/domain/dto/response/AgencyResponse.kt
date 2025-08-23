package com.vir.isekai.domain.dto.response

import java.time.LocalDate

class AgencyResponse {
	data class Detail(
		val agencyId: Long,
		val name: String,
		val logoImageUrl: String,
		val establishedDate: LocalDate,
		val closedDate: LocalDate?,
		val artistInfos: List<ArtistInfo>,
		val simples: List<ChannelResponse.Simple>,
	)

	data class ArtistInfo(
		val artistId: Long,
		val name: String,
		val logoImageUrl: String,
	)

	data class Simple(
		val agencyId: Long,
		val name: String,
		val logoImageUrl: String,
	)
}