package com.vir.isekai.dto.command

import com.vir.isekai.entity.Agency
import com.vir.isekai.entity.Vtuber
import com.vir.isekai.entity.enums.Generation
import com.vir.isekai.entity.enums.Platform
import java.time.LocalDate

class VtuberCommand {
	data class Save(
		val agencyId: Long?,
		val name: String,
		val profileImageUrl: String,
		val age: Int,
		val height: Int,
		val generation: Generation,
		val race: String,
		val channelInfos: List<ChannelCommand.Save>,
	) {
		fun toEntity(agency: Agency?): Vtuber {
			return Vtuber(
				null,
				agency,
				null,
				name,
				profileImageUrl,
				age,
				height,
				generation,
				race,
			)
		}
	}

	data class Detail(
		val vtuberId: Long,
		val name: String,
		val age: Int,
		val height: Int,
		var race: String,
		val platform: Platform,
		val debutDate: LocalDate?,
		val graduateDate: LocalDate?,
	)

	data class Simple(
		val vtuberId: Long,
		val name: String,
		val logoImageUrl: String,
	)

	data class FandomInfo(
		val fandomId: Long?,
		val name: String?,
		val logoImageUrl: String,
	)

	data class WithAgency(
		val vtuberId: Long,
		val vtuberName: String,
		val logoImageUrl: String,
		val agencyId: Long,
		val agencyName: String,
	)
}