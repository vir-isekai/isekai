package com.vir.isekai.controller

import com.vir.isekai.facade.MemberFacade
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/members")
class MemberController(
	private val memberFacade: MemberFacade,
) {
	@PostMapping("/{memberId}")
	fun saveMemberAdditionalInfo(
		@PathVariable memberId: Long,
	) {
	}
}