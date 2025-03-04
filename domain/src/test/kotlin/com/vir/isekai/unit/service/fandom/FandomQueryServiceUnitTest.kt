package com.vir.isekai.unit.service.fandom

import com.vir.isekai.dto.command.FandomCommand
import com.vir.isekai.entity.Agency
import com.vir.isekai.entity.Fandom
import com.vir.isekai.entity.Vtuber
import com.vir.isekai.repository.agency.AgencyRepository
import com.vir.isekai.repository.fandom.FandomRepository
import com.vir.isekai.repository.vtuber.VtuberRepository
import com.vir.isekai.service.fandom.FandomQueryService
import io.kotest.core.spec.style.StringSpec
import io.mockk.*
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
		val mockAgency = mockk<Agency>()
		val mockkFandom = mockk<Fandom>()

		every { agencyRepository.findByIdOrNull(1L) } returns mockAgency
		every { fandomRepository.save(any(Fandom::class)) } returns mockkFandom
		every { mockkFandom.linkAgency(mockAgency) } just Runs

		service.saveFandom(commandWithAgency)

		verify(exactly = 1) { agencyRepository.findByIdOrNull(1L) }
		verify(exactly = 1) { fandomRepository.save(any(Fandom::class)) }
		verify(exactly = 1) { mockkFandom.linkAgency(mockAgency) }
	}

	"버튜버 팬덤 저장 성공" {
		val mockVtuber = mockk<Vtuber>()
		val mockkFandom = mockk<Fandom>()

		every { vtuberRepository.findByIdOrNull(1L) } returns mockVtuber
		every { fandomRepository.save(any(Fandom::class)) } returns mockkFandom
		every { mockkFandom.linkVtuber(mockVtuber) } just Runs

		service.saveFandom(commandWithVtuber)

		verify(exactly = 1) { vtuberRepository.findByIdOrNull(1L) }
		verify(exactly = 1) { fandomRepository.save(any(Fandom::class)) }
		verify(exactly = 1) { mockkFandom.linkVtuber(mockVtuber) }
	}
})