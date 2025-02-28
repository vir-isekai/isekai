package com.vir.isekai.service.vtuber

import com.vir.isekai.dto.command.VtuberCommand
import com.vir.isekai.repository.vtuber.VtuberRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional(rollbackOn = [Exception::class])
class VtuberQueryService(
	private val vtuberRepository: VtuberRepository,
) {
	fun saveVtuber(command: VtuberCommand.Save) {
		vtuberRepository.save(command.toEntity())
	}
}