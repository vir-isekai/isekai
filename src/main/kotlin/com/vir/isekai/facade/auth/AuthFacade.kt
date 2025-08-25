package com.vir.isekai.facade.auth

import com.vir.isekai.domain.entity.member.Member
import com.vir.isekai.service.login.auth.AuthClient
import com.vir.isekai.service.member.MemberCommandService
import com.vir.isekai.service.member.MemberQueryService
import mu.KotlinLogging
import org.springframework.stereotype.Component

private val log = KotlinLogging.logger {}

@Component
class AuthFacade(
	private val authClient: AuthClient,
	private val memberCommandService: MemberCommandService,
	private val memberQueryService: MemberQueryService,
) {
	fun joinMemberOrLogin(code: String): Member {
		val snsMemberInfo = authClient.getMemberSaveDTO(code)

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
}