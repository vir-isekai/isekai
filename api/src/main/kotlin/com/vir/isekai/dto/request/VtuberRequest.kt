package com.vir.isekai.dto.request

import com.vir.isekai.dto.command.ChannelCommand
import com.vir.isekai.dto.command.VtuberCommand
import com.vir.isekai.entity.enums.ChannelType
import com.vir.isekai.entity.enums.Generation
import java.time.LocalDate

class VtuberRequest {
	data class Save(
		val agencyId: Long?,
		val name: String,
		val profileImageUrl: String,
		val age: Int,
		val height: Int,
		val generation: Generation,
		val debutDate: LocalDate?,
		var race: String,
		val channelInfos: List<ChannelInfo>,
	) {
		fun toCommand(): VtuberCommand.Save {
			val channelInfos =
				this.channelInfos.map {
					ChannelCommand.Save(
						it.type,
						it.url,
					)
				}

			return VtuberCommand.Save(
				agencyId,
				name,
				profileImageUrl,
				age,
				height,
				generation,
				race,
				debutDate,
				channelInfos,
			)
		}
	}

	data class ChannelInfo(
		val type: ChannelType,
		val url: String,
	)
}