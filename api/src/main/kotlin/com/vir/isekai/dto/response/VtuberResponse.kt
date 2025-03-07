package com.vir.isekai.dto.response

import com.vir.isekai.dto.command.ChannelCommand
import com.vir.isekai.dto.command.FandomCommand
import com.vir.isekai.dto.command.VtuberCommand
import com.vir.isekai.dto.response.AgencyResponse.ChannelInfo
import com.vir.isekai.entity.enums.Platform
import java.time.LocalDate

class VtuberResponse {
	data class Detail(
		val name: String,
		val age: Int,
		val height: Int,
		val fandom: FandomInfo?,
		var race: String,
		val debutDate: LocalDate?,
		val graduateDate: LocalDate?,
		val channelInfos: List<ChannelInfo>,
	) {
		companion object {
			fun from(
				command: VtuberCommand.Detail,
				fandomCommand: FandomCommand.Simple?,
				channelCommands: List<ChannelCommand.Simple>,
			): Detail {
				val fandomInfo =
					fandomCommand?.let {
						FandomInfo(
							it.fandomId,
							it.name,
						)
					}

				val channelInfos =
					channelCommands.map {
						ChannelInfo(
							it.type,
							it.url,
						)
					}

				return Detail(
					command.name,
					command.age,
					command.height,
					fandomInfo,
					command.race,
					command.debutDate,
					command.graduateDate,
					channelInfos,
				)
			}
		}
	}

	data class FandomInfo(
		val fandomId: Long?,
		val name: String?,
	)
}