package com.vir.isekai.dto.request

import com.vir.isekai.entity.enums.ChannelType
import com.vir.isekai.entity.enums.Generation
import java.time.LocalDate

class VtuberRequest {
	data class Save(
		val agencyId: Long?,
		val name: String,
		val profileImageUrl: String,
		val age: Int,
		val height: Int,
		val generation: Generation,
		val debutDate: LocalDate?,
		var race: String,
		val channelInfos: List<ChannelInfo>,
	)

	data class ChannelInfo(
		val type: ChannelType,
		val url: String,
	)
}