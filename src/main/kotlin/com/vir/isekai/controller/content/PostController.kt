package com.vir.isekai.controller.content

import com.vir.isekai.common.security.UserPrincipal
import com.vir.isekai.domain.dto.CommonResponse
import com.vir.isekai.domain.dto.request.PostRequest
import com.vir.isekai.facade.post.PostFacade
import mu.KotlinLogging
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

private val log = KotlinLogging.logger {}

@RestController
@RequestMapping("/api/posts")
class PostController(
	private val postFacade: PostFacade,
) {
	@PostMapping
	@PreAuthorize("hasRole('NORMAL')")
	fun savePost(
		@RequestBody request: PostRequest.Save,
		@AuthenticationPrincipal userInfo: UserPrincipal,
	): CommonResponse<Nothing> {
		postFacade.savePost(request, userInfo.getId())

		return CommonResponse.successSave()
	}

	@PutMapping
	@PreAuthorize("hasRole('NORMAL')")
	fun updatePost(
		@RequestBody request: PostRequest.Update,
		@AuthenticationPrincipal userInfo: UserPrincipal,
	) {
	}
}