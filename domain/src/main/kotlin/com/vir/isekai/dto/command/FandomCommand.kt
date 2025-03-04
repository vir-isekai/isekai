package com.vir.isekai.dto.command

import com.vir.isekai.entity.Agency
import com.vir.isekai.entity.Fandom
import com.vir.isekai.entity.Vtuber

class FandomCommand {
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
	}

	data class Simple(
		val fandomId: Long,
		val name: String,
	)
}