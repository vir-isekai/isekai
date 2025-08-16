package com.vir.isekai.service.member

import com.vir.isekai.domain.dto.response.MemberResponse
import com.vir.isekai.domain.entity.member.Member
import com.vir.isekai.repository.member.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class MemberQueryService(
	private val memberRepository: MemberRepository,
) {
	fun saveMember(response: MemberResponse.Save): Member {
		return memberRepository.save(response.toEntity())
	}
}