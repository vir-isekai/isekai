package com.vir.isekai.domain.dto.request

import com.vir.isekai.domain.entity.Agency
import com.vir.isekai.domain.entity.enums.ChannelType
import com.vir.isekai.domain.entity.enums.Nation
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
				name = name,
				logoImageUrl = logoImageUrl,
				nation = nation,
				establishedDate = establishedDate,
			)
		}
	}

	data class ChannelInfo(
		val type: ChannelType,
		val url: String,
	)
}