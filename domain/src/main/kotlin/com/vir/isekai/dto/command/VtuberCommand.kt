package com.vir.isekai.dto.command

import com.vir.isekai.entity.Vtuber
import com.vir.isekai.entity.enums.Platform
import com.vir.isekai.entity.enums.RaceType

class VtuberCommand {
	data class Save(
		val agencyId: Long?,
		val name: String,
		val age: Int,
		val height: Int,
		val fandom: String?,
		val race: RaceType,
		val platForm: Platform,
	) {
		fun toEntity(): Vtuber {
			return Vtuber(
				null,
				null,
				name,
				age,
				height,
				fandom,
				race,
				platForm,
			)
		}
	}

	data class Detail(
		val vtuberId: Long,
		val name: String,
		val age: Int,
		val height: Int,
		val fandom: String?,
		var race: RaceType,
		val platform: Platform,
	)
}