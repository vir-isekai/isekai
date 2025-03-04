package com.vir.isekai.dto.command

import com.vir.isekai.entity.enums.ChannelType

class ChannelCommand {
	data class Save(
		val type: ChannelType,
		val url: String,
	)

	data class Simple(
		val type: ChannelType,
		val url: String,
	)
}