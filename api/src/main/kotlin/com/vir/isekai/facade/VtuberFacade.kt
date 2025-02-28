package com.vir.isekai.facade

import com.vir.isekai.dto.request.VtuberDTO
import com.vir.isekai.service.vtuber.VtuberQueryService
import org.springframework.stereotype.Service

@Service
class VtuberFacade(
	private val vtuberQueryService: VtuberQueryService,
) {
	fun saveVtuber(request: VtuberDTO.SaveRequest) {
		vtuberQueryService.saveVtuber(request.toCommand())
	}
}