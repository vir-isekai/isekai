package com.vir.isekai.controller

import com.vir.isekai.domain.dto.CommonResponse
import com.vir.isekai.domain.dto.request.FandomRequest
import com.vir.isekai.facade.FandomFacade
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/fandoms")
class FandomController(
	private val fandomFacade: FandomFacade,
) {
	@PostMapping
	fun saveFandom(
		@RequestBody request: FandomRequest.Save,
	): CommonResponse<Nothing> {
		fandomFacade.saveFandom(request)

		return CommonResponse.successSave()
	}
}