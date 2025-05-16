package com.vir.isekai.service.member

import com.vir.isekai.entity.enums.SNSType
import com.vir.isekai.entity.member.Member
import com.vir.isekai.repository.member.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true, rollbackFor = [Exception::class])
class MemberCommandService(
	private val memberRepository: MemberRepository,
) {
	fun getMemberBySnsIdAndSNSType(
		snsId: String,
		snsType: SNSType,
	): Member? {
		return memberRepository.findBySnsIdAndSnsType(snsId, snsType)
	}
}