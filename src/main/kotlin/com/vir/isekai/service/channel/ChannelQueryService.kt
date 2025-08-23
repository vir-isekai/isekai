package com.vir.isekai.service.channel

import com.vir.isekai.repository.channel.ChannelCustomRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true, rollbackFor = [Exception::class])
class ChannelQueryService(
	private val channelCustomRepository: ChannelCustomRepository,
)