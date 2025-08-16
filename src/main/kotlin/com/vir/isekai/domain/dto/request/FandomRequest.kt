package com.vir.isekai.domain.dto.request

import com.vir.isekai.domain.entity.Agency
import com.vir.isekai.domain.entity.Artist
import com.vir.isekai.domain.entity.Fandom

class FandomRequest {
	data class Save(
		val agencyId: Long?,
		val artistId: Long?,
		val name: String,
		val logoImageUrl: String,
	) {
		fun toEntityWithAgency(agency: Agency): Fandom {
			return Fandom(
				null,
				agency,
				null,
				name,
				logoImageUrl,
			)
		}

		fun toEntityWithArtist(artist: Artist): Fandom {
			return Fandom(
				null,
				null,
				artist,
				name,
				logoImageUrl,
			)
		}

		fun toEntity(): Fandom {
			return Fandom(
				null,
				null,
				null,
				name,
				logoImageUrl,
			)
		}
	}
}