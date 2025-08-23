package com.vir.isekai.service.member

import com.vir.isekai.domain.entity.enums.SNSType
import com.vir.isekai.domain.entity.member.Member
import com.vir.isekai.repository.member.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true, rollbackFor = [Exception::class])
class MemberQueryService(
	private val memberRepository: MemberRepository,
) {
	fun getMemberById(memberId: Long): Member? {
		return memberRepository.findByIdOrNull(memberId)
	}

	fun getMemberBySnsIdAndSNSType(
		snsId: String,
		snsType: SNSType,
	): Member? {
		return memberRepository.findBySnsIdAndSnsType(snsId, snsType)
	}
}