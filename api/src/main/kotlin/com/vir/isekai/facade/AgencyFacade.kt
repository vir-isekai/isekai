package com.vir.isekai.facade

import com.vir.isekai.dto.request.AgencyRequest
import com.vir.isekai.dto.response.AgencyResponse
import com.vir.isekai.service.agency.AgencyCommandService
import com.vir.isekai.service.agency.AgencyQueryService
import com.vir.isekai.service.vtuber.VtuberCommandService
import org.springframework.stereotype.Service

@Service
class AgencyFacade(
	private val agencyCommandService: AgencyCommandService,
	private val agencyQueryService: AgencyQueryService,
	private val vtuberCommandService: VtuberCommandService,
) {
	fun getAgencyById(agencyId: Long): AgencyResponse.Detail {
		val command = agencyCommandService.getAgencyById(agencyId)

		val vtubers = vtuberCommandService.getVtubersByAgencyId(agencyId)

		return AgencyResponse.Detail.from(command, vtubers)
	}

	fun saveAgency(request: AgencyRequest.Save) {
		agencyQueryService.saveAgency(request.toCommand())
	}
}