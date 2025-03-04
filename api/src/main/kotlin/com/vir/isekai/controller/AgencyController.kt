package com.vir.isekai.controller

import com.vir.isekai.dto.CommonResponse
import com.vir.isekai.dto.request.AgencyRequest
import com.vir.isekai.dto.response.AgencyResponse
import com.vir.isekai.facade.AgencyFacade
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/agencies")
class AgencyController(
	private val agencyFacade: AgencyFacade,
) {
	@GetMapping("/{agencyId}")
	fun getAgencyById(
		@PathVariable agencyId: Long,
	): CommonResponse<AgencyResponse.Detail> {
		return CommonResponse.ok(agencyFacade.getAgencyById(agencyId))
	}

	@PostMapping
	fun saveAgency(
		@RequestBody request: AgencyRequest.Save,
	): CommonResponse<Nothing> {
		agencyFacade.saveAgency(request)

		return CommonResponse.successSave()
	}
}