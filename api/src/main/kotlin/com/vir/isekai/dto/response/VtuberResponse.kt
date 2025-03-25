package com.vir.isekai.dto.response

import com.vir.isekai.dto.command.AgencyCommand
import com.vir.isekai.dto.command.ChannelCommand
import com.vir.isekai.dto.command.FandomCommand
import com.vir.isekai.dto.command.VtuberCommand
import com.vir.isekai.dto.response.AgencyResponse.ChannelInfo
import com.vir.isekai.dto.response.VtuberResponse.AgencyInfo
import com.vir.isekai.dto.response.VtuberResponse.FandomInfo
import java.time.LocalDate

fun AgencyCommand.Simple.toAgencyInfo() =
	AgencyInfo(
		agencyId = agencyId,
		name = name,
		logoImageUrl = logoImageUrl,
	)

fun FandomCommand.Simple.toFandomInfo() =
	FandomInfo(
		fandomId = fandomId,
		name = name,
	)

fun ChannelCommand.Simple.toChannelInfo() =
	ChannelInfo(
		type = type,
		url = url,
	)

class VtuberResponse {
	data class Detail(
		val name: String,
		val profileImageUrl: String,
		val age: Int,
		val height: Int,
		var race: String,
		val debutDate: LocalDate?,
		val graduateDate: LocalDate?,
		val agencyInfo: AgencyInfo?,
		val fandom: FandomInfo?,
		val channelInfos: List<ChannelInfo>,
	) {
		companion object {
			fun from(
				command: VtuberCommand.Detail,
				agencyCommand: AgencyCommand.Simple?,
				fandomCommand: FandomCommand.Simple?,
				channelCommands: List<ChannelCommand.Simple>,
			): Detail {
				val agencyInfo = agencyCommand?.toAgencyInfo()
				val fandomInfo = fandomCommand?.toFandomInfo()
				val channelInfos = channelCommands.map(ChannelCommand.Simple::toChannelInfo)

				return Detail(
					command.name,
					command.profileImageUrl,
					command.age,
					command.height,
					command.race,
					command.debutDate,
					command.graduateDate,
					agencyInfo,
					fandomInfo,
					channelInfos,
				)
			}
		}
	}

	data class FandomInfo(
		val fandomId: Long?,
		val name: String?,
	)

	data class AgencyInfo(
		val agencyId: Long,
		val name: String,
		val logoImageUrl: String,
	)
}