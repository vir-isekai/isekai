package com.vir.isekai.service.agency

import com.vir.isekai.dto.command.AgencyCommand
import com.vir.isekai.entity.Channel
import com.vir.isekai.repository.agency.AgencyRepository
import com.vir.isekai.repository.channel.ChannelRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class AgencyQueryService(
	private val agencyRepository: AgencyRepository,
	private val channelRepository: ChannelRepository,
) {
	fun saveAgency(command: AgencyCommand.Save) {
		val agency = agencyRepository.save(command.toEntity())

		val channels =
			command.channelInfos.map {
				Channel(
					null,
					agency,
					null,
					it.type,
					it.url,
				)
			}

		channelRepository.saveAll(channels)
	}
}