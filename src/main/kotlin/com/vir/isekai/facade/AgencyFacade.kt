package com.vir.isekai.facade

import com.vir.isekai.domain.dto.request.AgencyRequest
import com.vir.isekai.domain.dto.response.AgencyResponse
import com.vir.isekai.service.agency.AgencyCommandService
import com.vir.isekai.service.agency.AgencyQueryService
import com.vir.isekai.service.artist.ArtistCommandService
import com.vir.isekai.service.channel.ChannelCommandService
import org.springframework.stereotype.Component

@Component
class AgencyFacade(
	private val agencyCommandService: AgencyCommandService,
	private val agencyQueryService: AgencyQueryService,
	private val channelCommandService: ChannelCommandService,
	private val artistCommandService: ArtistCommandService,
) {
	fun getAgencies(): List<AgencyResponse.Simple> {
		return agencyCommandService.getAgencies()
	}

	fun getAgencyById(agencyId: Long): AgencyResponse.Detail? {
		val agencyCommand = agencyCommandService.getAgencyById(agencyId)
		
		return null
	}

	fun saveAgency(request: AgencyRequest.Save) {
		agencyQueryService.saveAgency(request)
	}
}