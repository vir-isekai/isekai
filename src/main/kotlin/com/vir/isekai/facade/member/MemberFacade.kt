package com.vir.isekai.facade.member

import com.vir.isekai.domain.dto.request.MemberRequest
import com.vir.isekai.domain.entity.enums.member.BusinessType
import com.vir.isekai.service.agency.AgencyQueryService
import com.vir.isekai.service.artist.ArtistQueryService
import com.vir.isekai.service.member.MemberCommandService
import com.vir.isekai.service.member.MemberQueryService
import com.vir.isekai.service.member.businessprofile.BusinessProfileCommandService
import org.springframework.stereotype.Component

@Component
class MemberFacade(
	private val agencyQueryService: AgencyQueryService,
	private val artistQueryService: ArtistQueryService,
	private val businessProfileCommandService: BusinessProfileCommandService,
	private val memberCommandService: MemberCommandService,
	private val memberQueryService: MemberQueryService,
) {
	fun registerBusinessMember(request: MemberRequest.Business) {
		val member =
			memberQueryService.getMemberById(request.memberId)
				?: throw IllegalArgumentException("Member not found with id: ${request.memberId}")

		val agency =
			request.agencyId?.let {
				agencyQueryService.getAgencyById(it)
					?: throw IllegalArgumentException("Agency not found with id: $it")
			}

		val artist =
			request.artistId?.let {
				artistQueryService.getArtistById(it)
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