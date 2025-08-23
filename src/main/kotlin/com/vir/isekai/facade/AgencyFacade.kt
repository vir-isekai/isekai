package com.vir.isekai.facade

import com.vir.isekai.domain.dto.request.AgencyRequest
import com.vir.isekai.domain.dto.response.AgencyResponse
import com.vir.isekai.service.agency.AgencyCommandService
import com.vir.isekai.service.agency.AgencyQueryService
import com.vir.isekai.service.artist.ArtistCommandService
import com.vir.isekai.service.channel.ChannelQueryService
import org.springframework.stereotype.Component

@Component
class AgencyFacade(
	private val agencyCommandService: AgencyCommandService,
	private val agencyQueryService: AgencyQueryService,
	private val channelQueryService: ChannelQueryService,
	private val artistCommandService: ArtistCommandService,
) {
	fun getAgencies(): List<AgencyResponse.Simple> {
		return agencyQueryService.getAgencies()
	}

	fun getAgencyById(agencyId: Long): AgencyResponse.Detail? {
		val agencyCommand = agencyQueryService.getAgencyDetailById(agencyId)

		return null
	}

	fun saveAgency(request: AgencyRequest.Save) {
		agencyCommandService.saveAgency(request)
	}
}