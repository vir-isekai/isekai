package com.vir.isekai.controller.member

import com.vir.isekai.facade.MemberFacade
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/members")
class MemberController(
	private val memberFacade: MemberFacade,
)