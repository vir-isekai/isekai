package com.vir.isekai.dto.response

import com.vir.isekai.dto.command.AgencyCommand
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
	) {
		companion object {
			fun from(command: AgencyCommand.Detail): Detail {
				return Detail(
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