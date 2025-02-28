package com.vir.isekai.service.vtuber

import com.vir.isekai.dto.command.VtuberCommand
import com.vir.isekai.repository.vtuber.VtuberCustomRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true, rollbackFor = [Exception::class])
class VtuberCommandService(
	private val vtuberCustomRepository: VtuberCustomRepository,
) {
	fun getVtuberById(vtuberId: Long): VtuberCommand.Detail? {
		return vtuberCustomRepository.getVtuberById(vtuberId)
	}
}