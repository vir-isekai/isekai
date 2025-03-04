package com.vir.isekai.facade

import com.vir.isekai.dto.request.AgencyRequest
import com.vir.isekai.dto.response.AgencyResponse
import com.vir.isekai.service.agency.AgencyCommandService
import com.vir.isekai.service.agency.AgencyQueryService
import com.vir.isekai.service.fandom.FandomCommandService
import com.vir.isekai.service.vtuber.VtuberCommandService
import org.springframework.stereotype.Service

@Service
class AgencyFacade(
	private val agencyCommandService: AgencyCommandService,
	private val agencyQueryService: AgencyQueryService,
	private val fandomCommandService: FandomCommandService,
	private val vtuberCommandService: VtuberCommandService,
) {
	fun getAgencyById(agencyId: Long): AgencyResponse.Detail {
		val agencyCommand = agencyCommandService.getAgencyById(agencyId)
		val vtuberCommand = vtuberCommandService.getVtubersByAgencyId(agencyId)
		val fandomCommand = fandomCommandService.getFandomByAgencyId(agencyId)

		return AgencyResponse.Detail.from(agencyCommand, vtuberCommand, fandomCommand)
	}

	fun saveAgency(request: AgencyRequest.Save) {
		agencyQueryService.saveAgency(request.toCommand())
	}
}