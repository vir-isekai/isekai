package com.vir.isekai.controller.member

import com.vir.isekai.domain.dto.CommonResponse
import com.vir.isekai.domain.dto.request.MemberRequest
import com.vir.isekai.facade.member.MemberFacade
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/members")
class MemberController(
	private val memberFacade: MemberFacade,
) {
	@PostMapping("/business-profile")
	fun registerBusinessMember(
		@RequestBody request: MemberRequest.Business,
	): CommonResponse<Nothing> {
		memberFacade.registerBusinessMember(request)
		
		return CommonResponse.successSave()
	}
}