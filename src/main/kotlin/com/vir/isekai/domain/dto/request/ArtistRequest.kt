package com.vir.isekai.domain.dto.request

import com.vir.isekai.domain.entity.Agency
import com.vir.isekai.domain.entity.Artist
import com.vir.isekai.domain.entity.enums.ChannelType
import com.vir.isekai.domain.entity.enums.Generation
import java.time.LocalDate

class ArtistRequest {
	data class Save(
		val agencyId: Long?,
		val name: String,
		val profileImageUrl: String,
		val age: Int,
		val height: Int,
		val generation: Generation,
		val debutDate: LocalDate?,
		var race: String,
		val channelInfos: List<ChannelInfo>,
	) {
		fun toEntity(agency: Agency?): Artist {
			return Artist(
				null,
				agency,
				null,
				name,
				profileImageUrl,
				age,
				height,
				generation,
				race,
				debutDate,
				null,
			)
		}
	}

	data class ChannelInfo(
		val type: ChannelType,
		val url: String,
	)
}