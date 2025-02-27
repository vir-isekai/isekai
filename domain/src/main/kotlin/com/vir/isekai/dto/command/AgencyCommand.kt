package com.vir.isekai.dto.command

import com.vir.isekai.entity.Agency
import com.vir.isekai.entity.enums.Nation
import java.time.LocalDate

class AgencyCommand {
	data class Save(
		val name: String,
		val logoImageUrl: String,
		val nation: Nation,
		val establishedDate: LocalDate,
		val closedDate: LocalDate?,
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
}