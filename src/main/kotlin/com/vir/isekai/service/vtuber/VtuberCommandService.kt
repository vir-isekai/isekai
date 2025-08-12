package com.vir.isekai.service.vtuber

import com.vir.isekai.domain.dto.command.VtuberCommand
import com.vir.isekai.repository.vtuber.VtuberCustomRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true, rollbackFor = [Exception::class])
class VtuberCommandService(
	private val vtuberCustomRepository: VtuberCustomRepository,
) {
	fun getVtuberById(vtuberId: Long): VtuberCommand.Detail {
		return vtuberCustomRepository.getVtuberById(vtuberId) ?: throw IllegalArgumentException()
	}

	fun getVtubersByAgencyId(agencyId: Long): List<VtuberCommand.Simple> {
		return vtuberCustomRepository.getVtubersByAgencyId(agencyId)
	}
}