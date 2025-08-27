package com.vir.isekai.repository.member

import com.vir.isekai.domain.entity.enums.member.SNSType
import com.vir.isekai.domain.entity.member.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
	fun findBySnsIdAndSnsType(
		snsId: String,
		snsType: SNSType,
	): Member?
}