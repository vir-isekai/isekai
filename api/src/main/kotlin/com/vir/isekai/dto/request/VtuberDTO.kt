package com.vir.isekai.dto.request

import com.vir.isekai.dto.command.VtuberCommand
import com.vir.isekai.entity.enums.Platform
import com.vir.isekai.entity.enums.RaceType

class VtuberDTO {
	data class SaveRequest(
		val agencyId: Long?,
		val name: String,
		val age: Int,
		val height: Int,
		val fandom: String?,
		var race: RaceType,
		val platform: Platform,
	) {
		fun toCommand(): VtuberCommand.Save {
			return VtuberCommand.Save(
				agencyId,
				name,
				age,
				height,
				fandom,
				race,
				platform,
			)
		}
	}
}