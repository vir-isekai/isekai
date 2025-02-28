package com.vir.isekai.facade

import com.vir.isekai.dto.command.AgencyCommand
import com.vir.isekai.dto.request.AgencyDTO
import com.vir.isekai.service.agency.AgencyCommandService
import com.vir.isekai.service.agency.AgencyQueryService
import org.springframework.stereotype.Service

@Service
class AgencyFacade(
	private val agencyCommandService: AgencyCommandService,
	private val agencyQueryService: AgencyQueryService,
) {
	fun getAgencyById(agencyId: Long): AgencyCommand.Detail {
		return agencyCommandService.getAgencyById(agencyId)
	}

	fun saveAgency(request: AgencyDTO.SaveRequest) {
		agencyQueryService.saveAgency(request.toCommand())
	}
}