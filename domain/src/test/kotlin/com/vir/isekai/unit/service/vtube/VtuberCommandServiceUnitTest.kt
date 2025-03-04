package com.vir.isekai.unit.service.vtube

import com.vir.isekai.repository.vtuber.VtuberCustomRepository
import com.vir.isekai.service.vtuber.VtuberCommandService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class VtuberCommandServiceUnitTest : StringSpec({
	val vtuberCustomRepository: VtuberCustomRepository = mockk()

	lateinit var service: VtuberCommandService

	beforeTest {
		clearMocks(vtuberCustomRepository)
		service = VtuberCommandService(vtuberCustomRepository)
	}

	"버튜버 상세 조회 성공" {
		every { vtuberCustomRepository.getVtuberById(1L) } returns mockk()

		service.getVtuberById(1L)

		verify(exactly = 1) { vtuberCustomRepository.getVtuberById(1L) }
	}

	"버튜버 상세 조회 실패" {
		every { vtuberCustomRepository.getVtuberById(1L) } returns null

		shouldThrow<IllegalArgumentException> {
			service.getVtuberById(1L)
		}
	}
})