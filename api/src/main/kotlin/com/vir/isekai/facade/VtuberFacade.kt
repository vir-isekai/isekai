package com.vir.isekai.facade

import com.vir.isekai.dto.request.VtuberRequest
import com.vir.isekai.dto.response.VtuberResponse
import com.vir.isekai.service.fandom.FandomCommandService
import com.vir.isekai.service.vtuber.VtuberCommandService
import com.vir.isekai.service.vtuber.VtuberQueryService
import org.springframework.stereotype.Service

@Service
class VtuberFacade(
	private val fandomCommandService: FandomCommandService,
	private val vtuberCommandService: VtuberCommandService,
	private val vtuberQueryService: VtuberQueryService,
) {
	fun getVtuberById(vtuberId: Long): VtuberResponse.Detail {
		val vtuberCommand = vtuberCommandService.getVtuberById(vtuberId)
		val fandomCommand = fandomCommandService.getFandomByVtuberId(vtuberId)

		return VtuberResponse.Detail.from(vtuberCommand, fandomCommand)
	}

	fun saveVtuber(request: VtuberRequest.Save) {
		vtuberQueryService.saveVtuber(request.toCommand())
	}
}