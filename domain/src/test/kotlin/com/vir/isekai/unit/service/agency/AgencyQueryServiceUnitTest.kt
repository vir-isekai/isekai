package com.vir.isekai.unit.service.agency

import com.vir.isekai.dto.command.AgencyCommand
import com.vir.isekai.entity.Agency
import com.vir.isekai.entity.Channel
import com.vir.isekai.repository.agency.AgencyRepository
import com.vir.isekai.repository.channel.ChannelRepository
import com.vir.isekai.service.agency.AgencyQueryService
import io.kotest.core.spec.style.StringSpec
import io.mockk.*

class AgencyQueryServiceUnitTest : StringSpec({
	val agencyRepository: AgencyRepository = mockk()
	val channelRepository: ChannelRepository = mockk()

	lateinit var service: AgencyQueryService

	beforeTest {
		service = AgencyQueryService(agencyRepository, channelRepository)
	}

	"소속사 저장 성공" {
		val mockCommand = mockk<AgencyCommand.Save>(relaxed = true)
		val mockAgency = mockk<Agency>(relaxed = true) // 저장 후 반환될 Agency 객체
		val mockChannel1 = mockk<Channel>(relaxed = true)
		val mockChannel2 = mockk<Channel>(relaxed = true)
		val mockChannels = listOf(mockChannel1, mockChannel2) // 리스트 자체는 실제 List 사용

		every { agencyRepository.save(any(Agency::class)) } returns mockAgency
		every { channelRepository.saveAll(any<List<Channel>>()) } returns mockChannels

		service.saveAgency(mockCommand)

		verify(exactly = 1) { agencyRepository.save(any(Agency::class)) }
		verify(exactly = 1) { channelRepository.saveAll(any<List<Channel>>()) }
	}
})