package com.vir.isekai.dto.request

import com.vir.isekai.dto.command.AgencyCommand
import com.vir.isekai.entity.enums.Nation
import java.time.LocalDate

class AgencyRequest {
	data class Save(
		val name: String,
		val loginImageUrl: String,
		val nation: Nation,
		val establishedDate: LocalDate,
		val closedDate: LocalDate?,
	) {
		fun toCommand(): AgencyCommand.Save {
			return AgencyCommand.Save(
				name,
				loginImageUrl,
				nation,
				establishedDate,
				closedDate,
			)
		}
	}
}