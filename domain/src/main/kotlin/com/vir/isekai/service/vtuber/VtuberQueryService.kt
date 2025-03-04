package com.vir.isekai.service.vtuber

import com.vir.isekai.dto.command.VtuberCommand
import com.vir.isekai.repository.agency.AgencyRepository
import com.vir.isekai.repository.fandom.FandomRepository
import com.vir.isekai.repository.vtuber.VtuberRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
@Transactional(rollbackOn = [Exception::class])
class VtuberQueryService(
	private val agencyRepository: AgencyRepository,
	private val fandomRepository: FandomRepository,
	private val vtuberRepository: VtuberRepository,
) {
	fun saveVtuber(command: VtuberCommand.Save) {
		val agencyId = command.agencyId
		val fandomId = command.fandomId

		val agency =
			if (agencyId != null) {
				agencyRepository.findByIdOrNull(agencyId) ?: throw IllegalArgumentException()
			} else {
				null
			}

		val fandom =
			if (fandomId != null) {
				fandomRepository.findByIdOrNull(fandomId) ?: throw IllegalArgumentException()
			} else {
				null
			}

		vtuberRepository.save(command.toEntity(agency, fandom))
	}
}