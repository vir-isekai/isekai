package com.vir.isekai.dto.response

import com.vir.isekai.dto.command.AgencyCommand
import com.vir.isekai.dto.command.FandomCommand
import com.vir.isekai.dto.command.VtuberCommand
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
		val vtubersInfo: List<VtuberCommand.Simple>,
		val fandomInfo: FandomCommand.Simple?,
	) {
		companion object {
			fun from(
				command: AgencyCommand.Detail,
				vtubers: List<VtuberCommand.Simple>,
				fandomCommand: FandomCommand.Simple,
			): Detail {
				return Detail(
					command.agencyId,
					command.name,
					command.logoImageUrl,
					command.nation,
					command.establishedDate,
					command.closedDate,
					vtubers,
					fandomCommand,
				)
			}
		}
	}

	data class Vtuber(
		val vtuberId: Long,
		val name: String,
	)
}