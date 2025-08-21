package com.vir.isekai.domain.dto.request

import com.vir.isekai.domain.entity.Agency
import com.vir.isekai.domain.entity.Artist
import com.vir.isekai.domain.entity.enums.ChannelType
import java.time.LocalDate

class ArtistRequest {
	data class Save(
		val agencyId: Long?,
		val name: String,
		val profileImageUrl: String,
		val debutDate: LocalDate?,
		val channelInfos: List<ChannelInfo>,
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

	data class ChannelInfo(
		val type: ChannelType,
		val url: String,
	)
}