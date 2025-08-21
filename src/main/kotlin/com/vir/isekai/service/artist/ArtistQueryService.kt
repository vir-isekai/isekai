package com.vir.isekai.service.artist

import com.vir.isekai.domain.dto.request.ArtistRequest
import com.vir.isekai.domain.entity.Channel
import com.vir.isekai.repository.agency.AgencyRepository
import com.vir.isekai.repository.artist.ArtistRepository
import com.vir.isekai.repository.channel.ChannelRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
@Transactional(rollbackOn = [Exception::class])
class ArtistQueryService(
	private val agencyRepository: AgencyRepository,
	private val channelRepository: ChannelRepository,
	private val artistRepository: ArtistRepository,
) {
	fun saveArtist(request: ArtistRequest.Save) {
		val agencyId = request.agencyId

		val agency =
			if (agencyId != null) {
				agencyRepository.findByIdOrNull(agencyId) ?: throw IllegalArgumentException()
			} else {
				null
			}

		val artist = artistRepository.save(request.toEntity(agency))

		val channels =
			request.channelInfos.map {
				Channel(
					null,
					null,
					artist,
					it.type,
					it.url,
				)
			}

		channelRepository.saveAll(channels)
	}
}