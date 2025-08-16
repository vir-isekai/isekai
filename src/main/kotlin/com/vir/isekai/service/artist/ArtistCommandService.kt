package com.vir.isekai.service.artist

import com.vir.isekai.repository.artist.ArtistCustomRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true, rollbackFor = [Exception::class])
class ArtistCommandService(
	private val artistCustomRepository: ArtistCustomRepository,
)