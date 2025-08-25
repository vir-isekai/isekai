package com.vir.isekai.facade.post

import com.vir.isekai.domain.dto.request.PostRequest
import com.vir.isekai.service.post.PostCommandService
import org.springframework.stereotype.Component

@Component
class PostFacade(
	private val postCommandService: PostCommandService,
) {
	fun savePost(
		request: PostRequest.Save,
		loginMemberId: Long,
	) {
		TODO("Not yet implemented")
	}
}