package com.vir.isekai.service.member

import com.vir.isekai.dto.MemberSaveDTO
import com.vir.isekai.entity.member.Member
import com.vir.isekai.repository.member.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class MemberQueryService(
	private val memberRepository: MemberRepository,
) {
	fun saveMember(dto: MemberSaveDTO): Member {
		return memberRepository.save(dto.toEntity())
	}
}