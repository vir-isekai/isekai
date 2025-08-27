package com.vir.isekai.domain.dto.request

import com.vir.isekai.domain.entity.enums.MediaType
import com.vir.isekai.domain.entity.member.Member
import com.vir.isekai.domain.entity.post.Post
import com.vir.isekai.domain.entity.post.PostMedia

class PostRequest {
	data class Save(
		val title: String,
		val content: String,
		val mediaList: List<Media> = emptyList(),
	) {
		fun toEntity(member: Member): Post {
			return Post(
				null,
				member,
				mutableListOf(),
				title,
				content,
			)
		}
	}

	data class Update(
		val title: String,
		val content: String,
	)

	data class Media(
		val mediaType: MediaType,
		val contentType: String,
		val originalFileName: String,
		val originalUrl: String,
		val thumbnailUrl: String,
		val displayOrder: Int = 0,
	) {
		fun toEntity(post: Post): PostMedia {
			return PostMedia(
				null,
				post,
				mediaType,
				contentType,
				originalFileName,
				originalUrl,
				thumbnailUrl,
				displayOrder,
			)
		}
	}
}