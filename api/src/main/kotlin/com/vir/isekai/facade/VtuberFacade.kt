package com.vir.isekai.facade

import com.vir.isekai.dto.request.VtuberRequest
import com.vir.isekai.dto.response.VtuberResponse
import com.vir.isekai.service.vtuber.VtuberCommandService
import com.vir.isekai.service.vtuber.VtuberQueryService
import org.springframework.stereotype.Service

@Service
class VtuberFacade(
	private val vtuberQueryService: VtuberQueryService,
	private val vtuberCommandService: VtuberCommandService,
) {
	fun getVtuberById(vtuberId: Long): VtuberResponse.Detail {
		val command = vtuberCommandService.getVtuberById(vtuberId)
		return VtuberResponse.Detail.from(command)
	}

	fun saveVtuber(request: VtuberRequest.Save) {
		vtuberQueryService.saveVtuber(request.toCommand())
	}
}