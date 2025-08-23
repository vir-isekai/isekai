package com.vir.isekai.service.member.businessprofile

import com.vir.isekai.domain.entity.business.Agency
import com.vir.isekai.domain.entity.business.Artist
import com.vir.isekai.domain.entity.enums.member.BusinessType
import com.vir.isekai.domain.entity.member.BusinessProfile
import com.vir.isekai.domain.entity.member.Member
import com.vir.isekai.repository.member.businessprofile.BusinessProfileRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class BusinessProfileCommandService(
	private val businessProfileRepository: BusinessProfileRepository,
) {
	fun createBusinessProfile(
		member: Member,
		agency: Agency?,
		artist: Artist?,
		businessType: BusinessType,
	): BusinessProfile {
		return businessProfileRepository.save(
			BusinessProfile(
				member = member,
				agency = agency,
				artist = artist,
				type = businessType,
			),
		)
	}

	fun deleteBusinessProfile(member: Member) {
		val businessProfile = businessProfileRepository.findByMember(member)
		businessProfile?.let { businessProfileRepository.delete(it) }
	}
}