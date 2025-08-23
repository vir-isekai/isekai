package com.vir.isekai.domain.dto.response

import com.vir.isekai.domain.entity.enums.ChannelType

class ChannelResponse {
	data class Simple(
		val type: ChannelType,
		val url: String,
	)
}