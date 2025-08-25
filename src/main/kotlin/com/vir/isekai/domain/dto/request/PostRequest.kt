package com.vir.isekai.domain.dto.request

class PostRequest {
	data class Save(
		val title: String,
		val content: String,
	)
}