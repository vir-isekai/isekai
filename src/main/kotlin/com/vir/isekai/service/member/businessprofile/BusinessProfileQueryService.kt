package com.vir.isekai.service.member.businessprofile

import com.vir.isekai.domain.entity.member.BusinessProfile
import com.vir.isekai.domain.entity.member.Member
import com.vir.isekai.repository.member.businessprofile.BusinessProfileRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true, rollbackFor = [Exception::class])
class BusinessProfileQueryService(
	private val businessProfileRepository: BusinessProfileRepository,
) {
	fun getBusinessProfileByMember(member: Member): BusinessProfile? {
		return businessProfileRepository.findByMember(member)
	}

	fun existsBusinessProfile(member: Member): Boolean {
		return businessProfileRepository.existsByMember(member)
	}
}