package com.vir.isekai.service.home

import com.vir.isekai.dto.command.HomeCommand
import com.vir.isekai.repository.home.HomeCustomRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true, rollbackFor = [Exception::class])
class HomeCommandService(
	private val homeCustomRepository: HomeCustomRepository,
) {
	fun getHomeCommand(): HomeCommand {
		val agencyCount = homeCustomRepository.getAgencyCount()
		val vtuberCount = homeCustomRepository.getVtuberCount()
		val vtubersWithAgency = homeCustomRepository.getVtubersWithAgency()

		return HomeCommand(
			HomeCommand.CountInfo(
				agencyCount ?: 0L,
				vtuberCount ?: 0L,
				0L,
				0L,
			),
			vtubersWithAgency,
		)
	}
}