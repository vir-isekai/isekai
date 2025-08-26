package com.vir.isekai.service.post

import com.vir.isekai.domain.dto.request.PostRequest
import com.vir.isekai.domain.entity.post.Post
import com.vir.isekai.repository.post.PostMediaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class PostMediaCommandService(
	private val postMediaRepository: PostMediaRepository,
) {
	fun savePostMedia(
		mediaList: List<PostRequest.MediaRequest>,
		post: Post,
	) {
		for (media in mediaList) {
			postMediaRepository.save(media.toEntity(post))	
		}
	}
}