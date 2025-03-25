package com.vir.isekai.unit.service.vtuber

import com.vir.isekai.dto.command.VtuberCommand
import com.vir.isekai.entity.Channel
import com.vir.isekai.entity.Vtuber
import com.vir.isekai.entity.enums.Generation
import com.vir.isekai.repository.agency.AgencyRepository
import com.vir.isekai.repository.channel.ChannelRepository
import com.vir.isekai.repository.vtuber.VtuberRepository
import com.vir.isekai.service.vtuber.VtuberQueryService
import io.kotest.core.spec.style.StringSpec
import io.mockk.*
import org.springframework.data.repository.findByIdOrNull
import java.time.LocalDate

class VtuberQueryServiceUnitTest : StringSpec({
	val agencyRepository: AgencyRepository = mockk()
	val vtuberRepository: VtuberRepository = mockk()
	val channelRepository: ChannelRepository = mockk()

	lateinit var service: VtuberQueryService

	beforeTest {
		clearMocks(agencyRepository, channelRepository, vtuberRepository)

		service = VtuberQueryService(agencyRepository, channelRepository, vtuberRepository)
	}

	val command =
		VtuberCommand.Save(
			null,
			"버튜버",
			"",
			4,
			160,
			Generation.NONE,
			"인간",
			LocalDate.now(),
			mutableListOf(),
		)

	val commandWithAgency =
		VtuberCommand.Save(
			1L,
			"소속버튜버",
			"",
			4,
			160,
			Generation.FIRST,
			"인간",
			LocalDate.now(),
			mutableListOf(),
		)

	"소속 없는 버튜버 저장 성공" {
		val mockChannel1 = mockk<Channel>(relaxed = true)
		val mockChannel2 = mockk<Channel>(relaxed = true)
		val mockChannels = listOf(mockChannel1, mockChannel2) // 리스트 자체는 실제 List 사용

		every { vtuberRepository.save(any(Vtuber::class)) } returns mockk()
		every { channelRepository.saveAll(any<List<Channel>>()) } returns mockChannels

		service.saveVtuber(command)

		verify(exactly = 1) { vtuberRepository.save(any(Vtuber::class)) }
		verify(exactly = 1) { channelRepository.saveAll(any<List<Channel>>()) }
	}

	"소속 있는 버튜버 저장 성공" {
		val mockChannel1 = mockk<Channel>(relaxed = true)
		val mockChannel2 = mockk<Channel>(relaxed = true)
		val mockChannels = listOf(mockChannel1, mockChannel2) // 리스트 자체는 실제 List 사용

		every { agencyRepository.findByIdOrNull(commandWithAgency.agencyId) } returns mockk()
		every { vtuberRepository.save(any(Vtuber::class)) } returns mockk()
		every { channelRepository.saveAll(any<List<Channel>>()) } returns mockChannels

		service.saveVtuber(commandWithAgency)

		verify(exactly = 1) { agencyRepository.findByIdOrNull(commandWithAgency.agencyId) }
		verify(exactly = 1) { vtuberRepository.save(any(Vtuber::class)) }
		verify(exactly = 1) { channelRepository.saveAll(any<List<Channel>>()) }
	}
})