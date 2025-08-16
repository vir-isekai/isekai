package com.vir.isekai.service.home

import com.vir.isekai.domain.dto.response.HomeResponse
import com.vir.isekai.repository.home.HomeCustomRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true, rollbackFor = [Exception::class])
class HomeCommandService(
	private val homeCustomRepository: HomeCustomRepository,
) {
	fun getHomeCommand(): HomeResponse {
		val agencyCount = homeCustomRepository.getAgencyCount()
		val artistCount = homeCustomRepository.getArtistCount()
		val artistsWithAgency = homeCustomRepository.getArtistsWithAgency()

		return HomeResponse(
			HomeResponse.CountInfo(
				agencyCount ?: 0L,
				artistCount ?: 0L,
				0L,
				0L,
			),
			artistsWithAgency,
		)
	}
}