package com.vir.isekai.facade

import com.vir.isekai.dto.response.HomeResponse
import com.vir.isekai.service.home.HomeCommandService
import org.springframework.stereotype.Component

@Component
class HomeFacade(
	private val homeCommandService: HomeCommandService,
) {
	fun getHomeResponse(): HomeResponse {
		val command = homeCommandService.getHomeCommand()

		return HomeResponse.from(command)
	}
}