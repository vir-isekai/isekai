package com.vir.isekai.dto.request

import com.vir.isekai.entity.Agency
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
		fun toEntity(): Agency {
			return Agency(
				name,
				logoImageUrl,
				nation,
				establishedDate,
				closedDate,
			)
		}
	}

	data class ChannelInfo(
		val type: ChannelType,
		val url: String,
	)
}