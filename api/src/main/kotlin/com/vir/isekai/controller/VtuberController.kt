package com.vir.isekai.controller

import com.vir.isekai.dto.CommonResponse
import com.vir.isekai.dto.command.AgencyCommand
import com.vir.isekai.dto.request.VtuberDTO
import com.vir.isekai.facade.VtuberFacade
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/vtubers")
class VtuberController(
	private val vtuberFacade: VtuberFacade,
) {
	@GetMapping("/{vtuberId}")
	fun getVtuberById(
		@PathVariable vtuberId: Long,
	): CommonResponse<AgencyCommand.Detail> {
		return CommonResponse.ok()
	}

	@PostMapping
	fun saveVtuber(
		@RequestBody request: VtuberDTO.SaveRequest,
	): CommonResponse<Nothing> {
		vtuberFacade.saveVtuber(request)

		return CommonResponse.successSave()
	}
}