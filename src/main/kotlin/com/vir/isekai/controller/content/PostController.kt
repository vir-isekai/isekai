package com.vir.isekai.controller.content

import com.vir.isekai.common.security.UserPrincipal
import com.vir.isekai.domain.dto.CommonResponse
import mu.KotlinLogging
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

private val log = KotlinLogging.logger {}

@RestController
@RequestMapping("/api/posts")
class PostController() {
	@PostMapping
	@PreAuthorize("hasRole('NORMAL')")
	fun savePost(
		@AuthenticationPrincipal userInfo: UserPrincipal,
	): CommonResponse<Nothing> {
		return CommonResponse.successSave()
	}
}