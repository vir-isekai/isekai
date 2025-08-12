package com.vir.isekai.service.login.auth

import com.vir.isekai.domain.entity.enums.SNSType
import com.vir.isekai.domain.entity.member.Member
import com.vir.isekai.repository.member.MemberRepository
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
	private val memberRepository: MemberRepository,
) {
	fun findUserBySnsIdAndType(
		snsId: String,
		snsType: SNSType,
	): Member? {
		return memberRepository.findBySnsIdAndSnsType(snsId, snsType)
	}
}