package com.vir.isekai.facade

import com.vir.isekai.domain.dto.request.ArtistRequest
import com.vir.isekai.domain.dto.response.ArtistResponse
import com.vir.isekai.service.agency.AgencyCommandService
import com.vir.isekai.service.artist.ArtistCommandService
import com.vir.isekai.service.artist.ArtistQueryService
import com.vir.isekai.service.channel.ChannelCommandService
import com.vir.isekai.service.fandom.FandomCommandService
import org.springframework.stereotype.Component

@Component
class ArtistFacade(
	private val agencyCommandService: AgencyCommandService,
	private val channelCommandService: ChannelCommandService,
	private val fandomCommandService: FandomCommandService,
	private val artistCommandService: ArtistCommandService,
	private val artistQueryService: ArtistQueryService,
) {
	fun getArtistById(artistId: Long): ArtistResponse.Detail? {
		val agencyCommand = agencyCommandService.getAgencyById(artistId)

		return null
	}

	fun saveArtist(request: ArtistRequest.Save) {
		artistQueryService.saveArtist(request)
	}
}