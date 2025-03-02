package com.vir.isekai.facade

import com.vir.isekai.dto.request.AgencyRequest
import com.vir.isekai.dto.response.AgencyResponse
import com.vir.isekai.service.agency.AgencyCommandService
import com.vir.isekai.service.agency.AgencyQueryService
import org.springframework.stereotype.Service

@Service
class AgencyFacade(
	private val agencyCommandService: AgencyCommandService,
	private val agencyQueryService: AgencyQueryService,
) {
	fun getAgencyById(agencyId: Long): AgencyResponse.Detail {
		val command = agencyCommandService.getAgencyById(agencyId)
		return AgencyResponse.Detail.from(command)
	}

	fun saveAgency(request: AgencyRequest.Save) {
		agencyQueryService.saveAgency(request.toCommand())
	}
}