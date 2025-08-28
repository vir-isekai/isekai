package com.vir.isekai.facade.auth

import com.vir.isekai.domain.entity.enums.member.SNSType
import com.vir.isekai.domain.entity.member.Member
import com.vir.isekai.service.auth.OAuthProviderStrategy
import com.vir.isekai.service.member.MemberCommandService
import com.vir.isekai.service.member.MemberQueryService
import mu.KotlinLogging
import org.springframework.stereotype.Component

private val log = KotlinLogging.logger {}

@Component
class AuthFacade(
	private val oAuthProviderStrategy: OAuthProviderStrategy,
	private val memberCommandService: MemberCommandService,
	private val memberQueryService: MemberQueryService,
) {
	fun joinMemberOrLogin(
		code: String,
		providerName: String,
	): Member {
		val provider = SNSType.valueOf(providerName.uppercase())
		val snsMemberInfo = oAuthProviderStrategy.authenticateUser(provider, code)
		val existingMember = memberQueryService.getMemberBySnsIdAndSNSType(snsMemberInfo.snsId, snsMemberInfo.snsType)

		val member =
			if (existingMember == null) {
				memberCommandService.saveMember(snsMemberInfo)
			} else {
				existingMember.updateLastLoginAt()
				existingMember
			}

		return member
	}

	fun getMemberById(memberId: Long): Member {
		return memberQueryService.getMemberById(memberId)
			?: throw IllegalArgumentException("Member with id: $memberId not found")
	}
}