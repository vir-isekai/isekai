package com.vir.isekai.service.artist

import com.vir.isekai.domain.dto.response.ArtistResponse
import com.vir.isekai.repository.agency.AgencyCustomRepository
import com.vir.isekai.repository.artist.ArtistCustomRepository
import com.vir.isekai.repository.artist.ArtistRepository
import com.vir.isekai.repository.channel.ChannelCustomRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true, rollbackFor = [Exception::class])
class ArtistCommandService(
	private val artistRepository: ArtistRepository,
	private val agencyCustomRepository: AgencyCustomRepository,
	private val artistCustomRepository: ArtistCustomRepository,
	private val channelCustomRepository: ChannelCustomRepository,
) {
	fun getArtistById(artistId: Long): ArtistResponse.Detail {
		val artist = artistRepository.findArtistById(artistId) ?: throw IllegalArgumentException("존재하지 않는 아티스트")
		val agencyInfo = agencyCustomRepository.getAgencyByArtistId(artistId)
		val channelInfos = channelCustomRepository.getChannelsByArtistId(artistId)

		return ArtistResponse.Detail(
			name = artist.name,
			profileImageUrl = artist.profileImageUrl,
			debutDate = artist.debutDate,
			agencyInfo = agencyInfo,
			channelInfos,
		)
	}
}