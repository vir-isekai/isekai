package com.vir.isekai.dto.response

import com.vir.isekai.dto.command.FandomCommand
import com.vir.isekai.dto.command.VtuberCommand
import com.vir.isekai.entity.enums.Platform
import com.vir.isekai.entity.enums.Race

class VtuberResponse {
	data class Detail(
		val name: String,
		val age: Int,
		val height: Int,
		val fandom: FandomInfo?,
		var race: Race,
		val platform: Platform,
	) {
		companion object {
			fun from(
				command: VtuberCommand.Detail,
				fandomCommand: FandomCommand.Simple?,
			): Detail {
				val fandomInfo =
					fandomCommand?.let {
						FandomInfo(
							it.fandomId,
							it.name,
						)
					}

				return Detail(
					command.name,
					command.age,
					command.height,
					fandomInfo,
					command.race,
					command.platform,
				)
			}
		}
	}

	data class FandomInfo(
		val fandomId: Long?,
		val name: String?,
	)
}