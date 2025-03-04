package com.vir.isekai.unit.service.vtube

import com.vir.isekai.dto.command.VtuberCommand
import com.vir.isekai.entity.Vtuber
import com.vir.isekai.entity.enums.Generation
import com.vir.isekai.entity.enums.Platform
import com.vir.isekai.entity.enums.Race
import com.vir.isekai.repository.agency.AgencyRepository
import com.vir.isekai.repository.vtuber.VtuberRepository
import com.vir.isekai.service.vtuber.VtuberQueryService
import io.kotest.core.spec.style.StringSpec
import io.mockk.*
import org.springframework.data.repository.findByIdOrNull

class VtuberQueryServiceUnitTest : StringSpec({
	val agencyRepository: AgencyRepository = mockk()
	val vtuberRepository: VtuberRepository = mockk()

	lateinit var service: VtuberQueryService

	beforeTest {
		clearMocks(agencyRepository, vtuberRepository)
		service = VtuberQueryService(agencyRepository, vtuberRepository)
	}

	val command =
		VtuberCommand.Save(
			null,
			"버튜버",
			4,
			160,
			null,
			Generation.NONE,
			Race.HUMAN,
			Platform.CHZZK,
		)

	val commandWithAgency =
		VtuberCommand.Save(
			1L,
			"소속버튜버",
			4,
			160,
			null,
			Generation.FIRST,
			Race.HUMAN,
			Platform.CHZZK,
		)

	"소속 없는 버튜버 저장 성공" {
		every { vtuberRepository.save(any(Vtuber::class)) } returns mockk()

		service.saveVtuber(command)

		verify(exactly = 1) { vtuberRepository.save(any(Vtuber::class)) }
	}

	"소속 있는 버튜버 저장 성공" {
		every { agencyRepository.findByIdOrNull(commandWithAgency.agencyId) } returns mockk()
		every { vtuberRepository.save(any(Vtuber::class)) } returns mockk()

		service.saveVtuber(commandWithAgency)

		verify(exactly = 1) { agencyRepository.findByIdOrNull(commandWithAgency.agencyId) }
		verify(exactly = 1) { vtuberRepository.save(any(Vtuber::class)) }
	}
})