package com.vir.isekai.service.fandom

import com.vir.isekai.domain.dto.request.FandomRequest
import com.vir.isekai.repository.agency.AgencyRepository
import com.vir.isekai.repository.artist.ArtistRepository
import com.vir.isekai.repository.fandom.FandomRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
@Transactional(rollbackOn = [Exception::class])
class FandomQueryService(
	private val agencyRepository: AgencyRepository,
	private val fandomRepository: FandomRepository,
	private val artistRepository: ArtistRepository,
) {
	fun saveFandom(request: FandomRequest.Save) {
		val agencyId = request.agencyId
		val artistId = request.artistId

		if (agencyId !== null) {
			val agency = agencyRepository.findByIdOrNull(agencyId) ?: throw IllegalArgumentException()
			val fandom = fandomRepository.save(request.toEntityWithAgency(agency))

			fandom.linkAgency(agency)
		} else if (artistId !== null) {
			val artist = artistRepository.findByIdOrNull(artistId) ?: throw IllegalArgumentException()
			val fandom = fandomRepository.save(request.toEntityWithArtist(artist))

			fandom.linkArtist(artist)
		} else {
			fandomRepository.save(request.toEntity())
		}
	}
}