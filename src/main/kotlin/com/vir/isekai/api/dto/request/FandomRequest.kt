package com.vir.isekai.dto.request

import com.vir.isekai.dto.command.FandomCommand

class FandomRequest {
	data class Save(
		val agencyId: Long?,
		val vtuberId: Long?,
		val name: String,
		val logoImageUrl: String,
	) {
		fun toCommand(): FandomCommand.Save {
			return FandomCommand.Save(
				agencyId,
				vtuberId,
				name,
				logoImageUrl,
			)
		}
	}
}