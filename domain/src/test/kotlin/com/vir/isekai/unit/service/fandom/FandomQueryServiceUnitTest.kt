package com.vir.isekai.unit.service.fandom

import com.vir.isekai.dto.command.FandomCommand
import com.vir.isekai.entity.Fandom
import com.vir.isekai.repository.agency.AgencyRepository
import com.vir.isekai.repository.fandom.FandomRepository
import com.vir.isekai.repository.vtuber.VtuberRepository
import com.vir.isekai.service.fandom.FandomQueryService
import io.kotest.core.spec.style.StringSpec
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.data.repository.findByIdOrNull

class FandomQueryServiceUnitTest : StringSpec({
	val agencyRepository: AgencyRepository = mockk()
	val fandomRepository: FandomRepository = mockk()
	val vtuberRepository: VtuberRepository = mockk()

	lateinit var service: FandomQueryService

	beforeTest {
		clearMocks(agencyRepository, fandomRepository, vtuberRepository)
		service =
			FandomQueryService(
				agencyRepository,
				fandomRepository,
				vtuberRepository,
			)
	}

	val commandWithAgency =
		FandomCommand.Save(
			1L,
			null,
			"파스텔",
			"",
		)

	val commandWithVtuber =
		FandomCommand.Save(
			null,
			1L,
			"아르냥",
			"",
		)

	"소속사 팬덤 저장 성공" {
		every { agencyRepository.findByIdOrNull(1L) } returns mockk()
		every { fandomRepository.save(any(Fandom::class)) } returns mockk()

		service.saveFandom(commandWithAgency)

		verify(exactly = 1) { agencyRepository.findByIdOrNull(1L) }
		verify(exactly = 0) { vtuberRepository.findByIdOrNull(1L) }
		verify(exactly = 1) { fandomRepository.save(any(Fandom::class)) }
	}

	"버튜버 팬덤 저장 성공" {
		every { vtuberRepository.findByIdOrNull(1L) } returns mockk()
		every { fandomRepository.save(any(Fandom::class)) } returns mockk()

		service.saveFandom(commandWithVtuber)

		verify(exactly = 0) { agencyRepository.findByIdOrNull(1L) }
		verify(exactly = 1) { vtuberRepository.findByIdOrNull(1L) }
		verify(exactly = 1) { fandomRepository.save(any(Fandom::class)) }
	}
})