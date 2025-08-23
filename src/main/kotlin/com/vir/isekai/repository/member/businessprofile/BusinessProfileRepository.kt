package com.vir.isekai.repository.member.businessprofile

import com.vir.isekai.domain.entity.member.BusinessProfile
import com.vir.isekai.domain.entity.member.Member
import org.springframework.data.jpa.repository.JpaRepository

interface BusinessProfileRepository : JpaRepository<BusinessProfile, Long> {
	fun findByMember(member: Member): BusinessProfile?

	fun existsByMember(member: Member): Boolean
}