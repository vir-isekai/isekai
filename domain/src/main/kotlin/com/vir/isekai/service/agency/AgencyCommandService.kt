package com.vir.isekai.service.agency

import com.vir.isekai.dto.command.AgencyCommand
import com.vir.isekai.repository.agency.AgencyCustomRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true, rollbackFor = [Exception::class])
class AgencyCommandService(
	private val agencyCustomRepository: AgencyCustomRepository,
) {
	fun getAgencyById(agencyId: Long): AgencyCommand.Detail {
		return agencyCustomRepository.getAgencyById(agencyId) ?: throw IllegalArgumentException()
	}
}