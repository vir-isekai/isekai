package com.vir.isekai.facade

import com.vir.isekai.domain.dto.response.HomeResponse
import com.vir.isekai.service.home.HomeQueryService
import org.springframework.stereotype.Component

@Component
class HomeFacade(
	private val homeQueryService: HomeQueryService,
) {
	fun getHomeResponse(): HomeResponse {
		return homeQueryService.getHomeCommand()
	}
}