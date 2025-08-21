package com.vir.isekai.service.agency

import com.vir.isekai.domain.dto.response.AgencyResponse
import com.vir.isekai.repository.agency.AgencyCustomRepository
import com.vir.isekai.repository.artist.ArtistRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true, rollbackFor = [Exception::class])
class AgencyCommandService(
	private val artistRepository: ArtistRepository,
	private val agencyCustomRepository: AgencyCustomRepository,
) {
	fun getAgencies(): List<AgencyResponse.Entry> {
		return agencyCustomRepository.getAgencies()
	}

	fun getAgencyById(agencyId: Long): AgencyResponse.Detail? {
		return agencyCustomRepository.getAgencyById(agencyId)
	}
}