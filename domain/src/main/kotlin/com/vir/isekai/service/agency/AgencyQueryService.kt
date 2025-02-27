package com.vir.isekai.service.agency

import com.vir.isekai.dto.command.AgencyCommand
import com.vir.isekai.repository.agency.AgencyRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true, rollbackFor = [Exception::class])
class AgencyQueryService(
	private val agencyRepository: AgencyRepository,
) {
	fun saveAgency(command: AgencyCommand.Save) {
		agencyRepository.save(command.toEntity())
	}
}