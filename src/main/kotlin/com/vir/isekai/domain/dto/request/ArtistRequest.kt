package com.vir.isekai.domain.dto.request

import com.vir.isekai.domain.dto.response.ChannelResponse
import com.vir.isekai.domain.entity.business.Agency
import com.vir.isekai.domain.entity.business.Artist
import java.time.LocalDate

class ArtistRequest {
	data class Save(
		val agencyId: Long?,
		val name: String,
		val profileImageUrl: String,
		val debutDate: LocalDate?,
		val channelInfos: List<ChannelResponse.Simple>,
	) {
		fun toEntity(agency: Agency?): Artist {
			return Artist(
				null,
				agency,
				name,
				profileImageUrl,
				debutDate,
			)
		}
	}
}