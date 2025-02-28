package com.vir.isekai.dto.request

import com.vir.isekai.dto.command.AgencyCommand
import com.vir.isekai.entity.enums.Nation
import java.time.LocalDate

class AgencyDTO {
	data class SaveRequest(
		val name: String,
		val logoImageUrl: String,
		val nation: Nation,
		val establishedDate: LocalDate,
		val closedDate: LocalDate?,
	) {
		fun toCommand(): AgencyCommand.Save {
			return AgencyCommand.Save(
				name,
				logoImageUrl,
				nation,
				establishedDate,
				closedDate,
			)
		}
	}

	data class DetailResponse(
		val agencyId: Long,
		val name: String,
		val logoImageUrl: String,
		val nation: Nation,
		val establishedDate: LocalDate,
		val closedDate: LocalDate?,
	) {
		companion object {
			fun from(command: AgencyCommand.Detail): DetailResponse {
				return DetailResponse(
					command.agencyId,
					command.name,
					command.logoImageUrl,
					command.nation,
					command.establishedDate,
					command.closedDate,
				)
			}
		}
	}
}