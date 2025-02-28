package com.vir.isekai.facade

import com.vir.isekai.dto.request.VtuberDTO
import com.vir.isekai.service.vtuber.VtuberCommandService
import com.vir.isekai.service.vtuber.VtuberQueryService
import org.springframework.stereotype.Service

@Service
class VtuberFacade(
	private val vtuberQueryService: VtuberQueryService,
	private val vtuberCommandService: VtuberCommandService,
) {
	fun getVtuberById(vtuberId: Long): VtuberDTO.DetailResponse {
		val command = vtuberCommandService.getVtuberById(vtuberId)
		return VtuberDTO.DetailResponse.from(command)
	}

	fun saveVtuber(request: VtuberDTO.SaveRequest) {
		vtuberQueryService.saveVtuber(request.toCommand())
	}
}