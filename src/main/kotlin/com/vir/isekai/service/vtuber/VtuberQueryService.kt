package com.vir.isekai.service.vtuber

import com.vir.isekai.domain.dto.request.VtuberRequest
import com.vir.isekai.domain.entity.Channel
import com.vir.isekai.repository.agency.AgencyRepository
import com.vir.isekai.repository.channel.ChannelRepository
import com.vir.isekai.repository.vtuber.VtuberRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
@Transactional(rollbackOn = [Exception::class])
class VtuberQueryService(
	private val agencyRepository: AgencyRepository,
	private val channelRepository: ChannelRepository,
	private val vtuberRepository: VtuberRepository,
) {
	fun saveVtuber(command: VtuberRequest.Save) {
		val agencyId = command.agencyId

		val agency =
			if (agencyId != null) {
				agencyRepository.findByIdOrNull(agencyId) ?: throw IllegalArgumentException()
			} else {
				null
			}

		val vtuber = vtuberRepository.save(command.toEntity(agency))

		val channels =
			command.channelInfos.map {
				Channel(
					null,
					null,
					vtuber,
					it.type,
					it.url,
				)
			}

		channelRepository.saveAll(channels)
	}
}