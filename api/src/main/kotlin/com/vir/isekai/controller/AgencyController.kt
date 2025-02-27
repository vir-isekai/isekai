package com.vir.isekai.controller

import com.vir.isekai.dto.CommonResponse
import com.vir.isekai.facade.AgencyFacade
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/agencies")
class AgencyController(
	private val agencyFacade: AgencyFacade,
) {
	@PostMapping
	fun saveAgency(): CommonResponse<Nothing> {
		return CommonResponse.successSave()
	}
}