package com.vir.isekai.facade

import com.vir.isekai.domain.dto.response.HomeResponse
import com.vir.isekai.service.home.HomeCommandService
import org.springframework.stereotype.Component

@Component
class HomeFacade(
	private val homeCommandService: HomeCommandService,
) {
	fun getHomeResponse(): HomeResponse {
		return homeCommandService.getHomeCommand()
	}
}