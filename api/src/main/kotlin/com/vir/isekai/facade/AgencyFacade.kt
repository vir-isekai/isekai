package com.vir.isekai.facade

import com.vir.isekai.dto.request.AgencyRequest
import com.vir.isekai.service.agency.AgencyQueryService
import org.springframework.stereotype.Service

@Service
class AgencyFacade(
	private val agencyQueryService: AgencyQueryService,
) {
	fun saveAgency(request: AgencyRequest.Save) {
		agencyQueryService.saveAgency(request.toCommand())
	}
}