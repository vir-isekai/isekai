package com.vir.isekai.facade

import com.vir.isekai.domain.dto.request.FandomRequest
import com.vir.isekai.service.fandom.FandomQueryService
import org.springframework.stereotype.Component

@Component
class FandomFacade(
	private val fandomQueryService: FandomQueryService,
) {
	fun saveFandom(request: FandomRequest.Save) {
		fandomQueryService.saveFandom(request)
	}
}