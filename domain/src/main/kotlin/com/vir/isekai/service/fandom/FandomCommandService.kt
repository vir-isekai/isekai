package com.vir.isekai.service.fandom

import com.vir.isekai.dto.command.FandomCommand
import com.vir.isekai.repository.fandom.FandomCustomRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true, rollbackFor = [Exception::class])
class FandomCommandService(
	private val fandomCustomRepository: FandomCustomRepository,
) {
	fun getFandomByAgencyId(agencyId: Long): FandomCommand.Simple? {
		return fandomCustomRepository.getFandomByAgencyId(agencyId)
	}

	fun getFandomByVtuberId(vtuberId: Long): FandomCommand.Simple? {
		return fandomCustomRepository.getFandomByVtuberId(vtuberId)
	}
}