package com.vir.isekai.facade.member

import com.vir.isekai.domain.dto.request.MemberRequest
import com.vir.isekai.domain.entity.enums.member.BusinessType
import com.vir.isekai.service.agency.AgencyCommandService
import com.vir.isekai.service.artist.ArtistCommandService
import com.vir.isekai.service.member.MemberCommandService
import com.vir.isekai.service.member.businessprofile.BusinessProfileCommandService
import org.springframework.stereotype.Component

@Component
class MemberFacade(
	private val memberCommandService: MemberCommandService,
	private val businessProfileCommandService: BusinessProfileCommandService,
	private val agencyCommandService: AgencyCommandService,
	private val artistCommandService: ArtistCommandService,
) {
	fun registerBusinessMember(request: MemberRequest.Business) {
		val member =
			memberCommandService.getMemberById(request.memberId)
				?: throw IllegalArgumentException("Member not found with id: ${request.memberId}")

		val agency =
			request.agencyId?.let {
				agencyCommandService.getAgencyById(it)
					?: throw IllegalArgumentException("Agency not found with id: $it")
			}

		val artist =
			request.artistId?.let {
				artistCommandService.getArtistById(it)
					?: throw IllegalArgumentException("Artist not found with id: $it")
			}

		require(
			(request.businessType == BusinessType.AGENCY && agency != null && artist == null) ||
				(request.businessType == BusinessType.ARTIST && artist != null && agency == null),
		) { "BusinessType must match the provided entity: ${request.businessType}" }

		businessProfileCommandService.createBusinessProfile(
			member = member,
			agency = agency,
			artist = artist,
			businessType = request.businessType,
		)
	}
}