package com.vir.isekai.domain.dto.response

data class HomeResponse(
	val countInfo: CountInfo,
	val popularArtistInfos: List<PopularArtistInfo>,
) {
	data class CountInfo(
		val agency: Long,
		val artist: Long,
		val member: Long,
		val visitor: Long,
	)

	data class PopularArtistInfo(
		val artistId: Long,
		val artistName: String,
		val profileImageUrl: String,
		val agencyId: Long,
		val agencyName: String,
	)
}