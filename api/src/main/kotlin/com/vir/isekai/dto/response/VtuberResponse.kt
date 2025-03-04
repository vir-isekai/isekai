package com.vir.isekai.dto.response

import com.vir.isekai.dto.command.VtuberCommand
import com.vir.isekai.entity.enums.Platform
import com.vir.isekai.entity.enums.Race

class VtuberResponse {
	data class Detail(
		val name: String,
		val age: Int,
		val height: Int,
		val fandom: String?,
		var race: Race,
		val platform: Platform,
	) {
		companion object {
			fun from(command: VtuberCommand.Detail): Detail {
				return Detail(
					command.name,
					command.age,
					command.height,
					command.fandom,
					command.race,
					command.platform,
				)
			}
		}
	}
}