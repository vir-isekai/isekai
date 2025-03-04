package com.vir.isekai.dto.response

import com.vir.isekai.dto.command.AgencyCommand
import com.vir.isekai.dto.command.ChannelCommand
import com.vir.isekai.dto.command.FandomCommand
import com.vir.isekai.dto.command.VtuberCommand
import com.vir.isekai.entity.enums.ChannelType
import com.vir.isekai.entity.enums.Nation
import java.time.LocalDate

class AgencyResponse {
	data class Detail(
		val agencyId: Long,
		val name: String,
		val logoImageUrl: String,
		val nation: Nation,
		val establishedDate: LocalDate,
		val closedDate: LocalDate?,
		val vtuberInfos: List<VtuberInfo>,
		val fandomInfo: FandomInfo?,
		val channelInfos: List<ChannelInfo>,
	) {
		companion object {
			fun from(
				command: AgencyCommand.Detail,
				vtuberCommands: List<VtuberCommand.Simple>,
				fandomCommand: FandomCommand.Simple?,
				channelCommands: List<ChannelCommand.Simple>,
			): Detail {
				val vtuberInfos =
					vtuberCommands.map { it ->
						VtuberInfo(
							it.vtuberId,
							it.name,
						)
					}

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
					command.agencyId,
					command.name,
					command.logoImageUrl,
					command.nation,
					command.establishedDate,
					command.closedDate,
					vtuberInfos,
					fandomInfo,
					channelInfos,
				)
			}
		}
	}

	data class VtuberInfo(
		val vtuberId: Long,
		val name: String,
	)

	data class FandomInfo(
		val fandomId: Long,
		val name: String,
	)

	data class ChannelInfo(
		val type: ChannelType,
		val url: String,
	)
}