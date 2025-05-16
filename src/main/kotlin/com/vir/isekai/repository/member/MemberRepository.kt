package com.vir.isekai.repository.member

import com.vir.isekai.entity.enums.SNSType
import com.vir.isekai.entity.member.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
	fun findBySnsIdAndSnsType(
		snsId: String,
		snsType: SNSType,
	): Member?
}