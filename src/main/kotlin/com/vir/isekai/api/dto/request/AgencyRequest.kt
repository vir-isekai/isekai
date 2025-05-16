package com.vir.isekai.dto.request

import com.vir.isekai.dto.command.AgencyCommand
import com.vir.isekai.dto.command.ChannelCommand
import com.vir.isekai.entity.enums.ChannelType
import com.vir.isekai.entity.enums.Nation
import java.time.LocalDate

class AgencyRequest {
	data class Save(
		val name: String,
		val logoImageUrl: String,
		val nation: Nation,
		val establishedDate: LocalDate,
		val closedDate: LocalDate?,
		val channelInfos: List<ChannelInfo>,
	) {
		fun toCommand(): AgencyCommand.Save {
			val channelInfos =
				this.channelInfos.map {
					ChannelCommand.Save(
						it.type,
						it.url,
					)
				}

			return AgencyCommand.Save(
				name,
				logoImageUrl,
				nation,
				establishedDate,
				closedDate,
				channelInfos,
			)
		}
	}

	data class ChannelInfo(
		val type: ChannelType,
		val url: String,
	)
}