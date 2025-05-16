package com.vir.isekai.facade

import com.vir.isekai.dto.request.VtuberRequest
import com.vir.isekai.dto.response.VtuberResponse
import com.vir.isekai.service.agency.AgencyCommandService
import com.vir.isekai.service.channel.ChannelCommandService
import com.vir.isekai.service.fandom.FandomCommandService
import com.vir.isekai.service.vtuber.VtuberCommandService
import com.vir.isekai.service.vtuber.VtuberQueryService
import org.springframework.stereotype.Component

@Component
class VtuberFacade(
	private val agencyCommandService: AgencyCommandService,
	private val channelCommandService: ChannelCommandService,
	private val fandomCommandService: FandomCommandService,
	private val vtuberCommandService: VtuberCommandService,
	private val vtuberQueryService: VtuberQueryService,
) {
	fun getVtuberById(vtuberId: Long): VtuberResponse.Detail {
		val agencyCommand = agencyCommandService.getAgencyByVtuberId(vtuberId)
		val vtuberCommand = vtuberCommandService.getVtuberById(vtuberId)
		val fandomCommand = fandomCommandService.getFandomByVtuberId(vtuberId)
		val channelCommand = channelCommandService.getChannelsByVtuberId(vtuberId)

		return VtuberResponse.Detail.from(
			vtuberCommand,
			agencyCommand,
			fandomCommand,
			channelCommand,
		)
	}

	fun saveVtuber(request: VtuberRequest.Save) {
		vtuberQueryService.saveVtuber(request.toCommand())
	}
}