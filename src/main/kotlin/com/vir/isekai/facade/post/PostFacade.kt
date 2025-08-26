package com.vir.isekai.facade.post

import com.vir.isekai.domain.dto.request.PostRequest
import com.vir.isekai.service.post.PostCommandService
import com.vir.isekai.service.post.PostMediaCommandService
import org.springframework.stereotype.Component

@Component
class PostFacade(
	private val postCommandService: PostCommandService,
	private val postMediaCommandService: PostMediaCommandService,
) {
	fun savePost(
		request: PostRequest.Save,
		loginMemberId: Long,
	) {
		val post = postCommandService.savePost(request, loginMemberId)
		
		postMediaCommandService.savePostMedia(request.mediaList, post)
	}
}