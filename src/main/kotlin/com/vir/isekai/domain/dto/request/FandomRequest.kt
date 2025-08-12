package com.vir.isekai.domain.dto.request

import com.vir.isekai.domain.entity.Agency
import com.vir.isekai.domain.entity.Fandom
import com.vir.isekai.domain.entity.Vtuber

class FandomRequest {
	data class Save(
		val agencyId: Long?,
		val vtuberId: Long?,
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

		fun toEntityWithVtuber(vtuber: Vtuber): Fandom {
			return Fandom(
				null,
				null,
				vtuber,
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