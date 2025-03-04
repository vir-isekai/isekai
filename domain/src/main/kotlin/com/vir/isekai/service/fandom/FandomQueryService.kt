package com.vir.isekai.service.fandom

import com.vir.isekai.dto.command.FandomCommand
import com.vir.isekai.repository.agency.AgencyRepository
import com.vir.isekai.repository.fandom.FandomRepository
import com.vir.isekai.repository.vtuber.VtuberRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
@Transactional(rollbackOn = [Exception::class])
class FandomQueryService(
	private val agencyRepository: AgencyRepository,
	private val fandomRepository: FandomRepository,
	private val vtuberRepository: VtuberRepository,
) {
	fun saveFandom(command: FandomCommand.Save) {
		val agencyId = command.agencyId
		val vtuberId = command.vtuberId

		if (agencyId !== null) {
			val agency = agencyRepository.findByIdOrNull(agencyId) ?: throw IllegalArgumentException()

			val fandom = fandomRepository.save(command.toEntityWithAgency(agency))

			fandom.linkAgency(agency)
		} else if (vtuberId !== null) {
			val vtuber = vtuberRepository.findByIdOrNull(vtuberId) ?: throw IllegalArgumentException()

			val fandom = fandomRepository.save(command.toEntityWithVtuber(vtuber))

			fandom.linkVtuber(vtuber)
		} else {
			fandomRepository.save(command.toEntity())
		}
	}
}