package com.vir.isekai.dto.request

import com.vir.isekai.dto.command.VtuberCommand
import com.vir.isekai.entity.enums.Generation
import com.vir.isekai.entity.enums.Platform

class VtuberRequest {
	data class Save(
		val agencyId: Long?,
		val name: String,
		val profileImageUrl: String,
		val age: Int,
		val height: Int,
		val generation: Generation,
		var race: String,
		val platform: Platform,
	) {
		fun toCommand(): VtuberCommand.Save {
			return VtuberCommand.Save(
				agencyId,
				name,
				profileImageUrl,
				age,
				height,
				generation,
				race,
				platform,
			)
		}
	}
}