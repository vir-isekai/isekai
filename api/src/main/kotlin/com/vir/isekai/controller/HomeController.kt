package com.vir.isekai.controller

import com.vir.isekai.dto.CommonResponse
import com.vir.isekai.dto.response.HomeResponse
import com.vir.isekai.facade.HomeFacade
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/home")
class HomeController(
	private val homeFacade: HomeFacade,
) {
	@GetMapping
	fun getHomeResponse(): CommonResponse<HomeResponse> {
		return CommonResponse.ok(homeFacade.getHomeResponse())
	}
}