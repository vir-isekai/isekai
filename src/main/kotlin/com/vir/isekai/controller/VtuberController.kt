package com.vir.isekai.controller

import com.vir.isekai.domain.dto.CommonResponse
import com.vir.isekai.domain.dto.request.VtuberRequest
import com.vir.isekai.domain.dto.response.VtuberResponse
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
	): CommonResponse<VtuberResponse.Detail?> {
		return CommonResponse.ok(vtuberFacade.getVtuberById(vtuberId))
	}

	@PostMapping
	fun saveVtuber(
		@RequestBody request: VtuberRequest.Save,
	): CommonResponse<Nothing> {
		vtuberFacade.saveVtuber(request)

		return CommonResponse.successSave()
	}
}