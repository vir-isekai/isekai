package com.vir.isekai.service.artist

import com.vir.isekai.domain.dto.response.ArtistResponse
import com.vir.isekai.repository.artist.ArtistCustomRepository
import com.vir.isekai.repository.artist.ArtistRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true, rollbackFor = [Exception::class])
class ArtistCommandService(
	private val artistRepository: ArtistRepository,
	private val artistCustomRepository: ArtistCustomRepository,
) {
	fun getArtistById(artistId: Long): ArtistResponse.Detail {
		val artist = artistRepository.findArtistById(artistId) ?: throw IllegalArgumentException("존재하지 않는 아티스트")
		
		return ArtistResponse.Detail(
			name = artist.name,
			profileImageUrl = artist.profileImageUrl,
			debutDate = artist.debutDate,
			null,
			null,
			emptyList(),
		)
	}
}