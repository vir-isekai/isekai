package com.vir.isekai.repository.member

import com.vir.isekai.entity.member.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
	fun findBySnsId(snsId: String): Member?
}