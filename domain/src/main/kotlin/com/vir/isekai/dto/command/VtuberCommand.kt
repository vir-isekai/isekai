package com.vir.isekai.dto.command

import com.vir.isekai.entity.Agency
import com.vir.isekai.entity.Fandom
import com.vir.isekai.entity.Vtuber
import com.vir.isekai.entity.enums.Generation
import com.vir.isekai.entity.enums.Platform
import com.vir.isekai.entity.enums.Race

class VtuberCommand {
	data class Save(
		val agencyId: Long?,
		val fandomId: Long?,
		val name: String,
		val age: Int,
		val height: Int,
		val generation: Generation,
		val race: Race,
		val platForm: Platform,
	) {
		fun toEntity(
			agency: Agency?,
			fandom: Fandom?,
		): Vtuber {
			return Vtuber(
				null,
				agency,
				fandom,
				name,
				age,
				height,
				generation,
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
		var race: Race,
		val platform: Platform,
	)

	data class Simple(
		val vtuberId: Long,
		val name: String,
	)
}