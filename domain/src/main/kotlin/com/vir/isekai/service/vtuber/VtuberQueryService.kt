package com.vir.isekai.service.vtuber

import com.vir.isekai.dto.command.VtuberCommand
import com.vir.isekai.repository.agency.AgencyRepository
import com.vir.isekai.repository.vtuber.VtuberRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
@Transactional(rollbackOn = [Exception::class])
class VtuberQueryService(
	private val vtuberRepository: VtuberRepository,
	private val agencyRepository: AgencyRepository,
) {
	fun saveVtuber(command: VtuberCommand.Save) {
		val agencyId = command.agencyId

		val agency =
			if (agencyId != null) {
				agencyRepository.findByIdOrNull(agencyId) ?: throw IllegalArgumentException()
			} else {
				null
			}

		vtuberRepository.save(command.toEntity(agency))
	}
}