package com.vir.isekai.service.post

import com.vir.isekai.domain.dto.request.PostRequest
import com.vir.isekai.domain.entity.post.Post
import com.vir.isekai.repository.member.MemberRepository
import com.vir.isekai.repository.post.PostRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class PostCommandService(
	private val postRepository: PostRepository,
	private val memberRepository: MemberRepository,
) {
	fun savePost(
		request: PostRequest.Save,
		loginMemberId: Long,
	): Post {
		val member =
			memberRepository.findByIdOrNull(loginMemberId)
				?: throw IllegalArgumentException("Member Not Found")
		
		val post = request.toEntity(member)
		
		return postRepository.save(post)
	}
}