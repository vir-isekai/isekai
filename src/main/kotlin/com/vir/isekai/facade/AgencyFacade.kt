package com.vir.isekai.facade

import com.vir.isekai.domain.dto.request.AgencyRequest
import com.vir.isekai.domain.dto.response.AgencyResponse
import com.vir.isekai.service.agency.AgencyCommandService
import com.vir.isekai.service.agency.AgencyQueryService
import com.vir.isekai.service.channel.ChannelCommandService
import com.vir.isekai.service.fandom.FandomCommandService
import com.vir.isekai.service.vtuber.VtuberCommandService
import org.springframework.stereotype.Component

@Component
class AgencyFacade(
	private val agencyCommandService: AgencyCommandService,
	private val agencyQueryService: AgencyQueryService,
	private val channelCommandService: ChannelCommandService,
	private val fandomCommandService: FandomCommandService,
	private val vtuberCommandService: VtuberCommandService,
) {
	fun getAgencies(): List<AgencyResponse.Entry> {
		return agencyCommandService.getAgencies()
	}

	fun getAgencyById(agencyId: Long): AgencyResponse.Detail? {
		val agencyCommand = agencyCommandService.getAgencyById(agencyId)
		
		return null
// 		return AgencyResponse.Detail(
// 			agencyCommand.,
// 			vtuberCommand,
// 			fandomCommand,
// 			channelCommand,
// 		)
	}

	fun saveAgency(request: AgencyRequest.Save) {
		agencyQueryService.saveAgency(request)
	}
}