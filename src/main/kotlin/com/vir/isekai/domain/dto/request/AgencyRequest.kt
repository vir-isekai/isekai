package com.vir.isekai.domain.dto.request

import com.vir.isekai.domain.dto.response.ChannelResponse
import com.vir.isekai.domain.entity.business.Agency
import com.vir.isekai.domain.entity.enums.Nation
import java.time.LocalDate

class AgencyRequest {
	data class Save(
		val name: String,
		val logoImageUrl: String,
		val nation: Nation,
		val establishedDate: LocalDate,
		val channelInfos: List<ChannelResponse.Simple>,
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
}