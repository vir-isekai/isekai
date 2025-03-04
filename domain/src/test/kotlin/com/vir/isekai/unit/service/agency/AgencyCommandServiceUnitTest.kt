package com.vir.isekai.unit.service.agency

import com.vir.isekai.repository.agency.AgencyCustomRepository
import com.vir.isekai.service.agency.AgencyCommandService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class AgencyCommandServiceUnitTest : StringSpec({
	val agencyCustomRepository: AgencyCustomRepository = mockk()

	lateinit var service: AgencyCommandService

	beforeTest {
		service = AgencyCommandService(agencyCustomRepository)
	}

	"소속사 상세 조회 성공" {
		every { agencyCustomRepository.getAgencyById(1L) } returns mockk()

		service.getAgencyById(1L)

		verify(exactly = 1) { agencyCustomRepository.getAgencyById(1L) }
	}

	"소속사 상세 조회 실패" {
		every { agencyCustomRepository.getAgencyById(1L) } returns null

		shouldThrow<IllegalArgumentException> {
			service.getAgencyById(1L)
		}
	}
})