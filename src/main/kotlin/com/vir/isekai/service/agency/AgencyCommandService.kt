package com.vir.isekai.service.agency

import com.vir.isekai.domain.dto.response.AgencyResponse
import com.vir.isekai.domain.entity.business.Agency
import com.vir.isekai.repository.agency.AgencyCustomRepository
import com.vir.isekai.repository.agency.AgencyRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true, rollbackFor = [Exception::class])
class AgencyCommandService(
	private val agencyRepository: AgencyRepository,
	private val agencyCustomRepository: AgencyCustomRepository,
) {
	fun getAgencies(): List<AgencyResponse.Simple> {
		return agencyCustomRepository.getAgencies()
	}

	fun getAgencyById(agencyId: Long): Agency? {
		return agencyRepository.findByIdOrNull(agencyId)
	}

	fun getAgencyDetailById(agencyId: Long): AgencyResponse.Detail? {
		return agencyCustomRepository.getAgencyById(agencyId)
	}
}