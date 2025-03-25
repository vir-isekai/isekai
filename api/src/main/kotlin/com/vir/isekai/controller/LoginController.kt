package com.vir.isekai.controller

import com.vir.isekai.dto.CommonResponse
import com.vir.isekai.facade.login.KakaoLoginFacade
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/login")
class LoginController(
	private val kakaoLoginFacade: KakaoLoginFacade,
) {
	@RequestMapping("/kakao/callback")
	fun callbackKakaoLogin(
		@RequestParam code: String,
	): CommonResponse<String> {
		return CommonResponse.ok(kakaoLoginFacade.generateKakaoToken(code))
	}
}