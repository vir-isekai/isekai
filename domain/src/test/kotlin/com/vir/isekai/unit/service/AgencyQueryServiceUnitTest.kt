package com.vir.isekai.unit.service

import com.vir.isekai.dto.command.AgencyCommand
import com.vir.isekai.entity.Agency
import com.vir.isekai.entity.enums.Nation
import com.vir.isekai.repository.agency.AgencyRepository
import com.vir.isekai.service.agency.AgencyQueryService
import io.kotest.core.spec.style.StringSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.time.LocalDate

class AgencyQueryServiceUnitTest : StringSpec({
	val agencyRepository: AgencyRepository = mockk()

	lateinit var agencyQueryService: AgencyQueryService

	beforeTest {
		agencyQueryService = AgencyQueryService(agencyRepository)
	}

	val command =
		AgencyCommand.Save(
			"회사1",
			"",
			Nation.KOREA,
			LocalDate.now(),
			null,
		)

	"소속사 저장" {
		every { agencyRepository.save(any(Agency::class)) } returns mockk()

		agencyQueryService.saveAgency(command)

		verify(exactly = 1) { agencyRepository.save(any(Agency::class)) }
	}
})