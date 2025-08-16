package com.vir.isekai.facade.auth

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
	fun joinMemberOrLogin(code: String): String {
		val snsMemberInfo = authClient.getMemberSaveDTO(code)

		log.info { "Joined Member Or Login: $snsMemberInfo" }
		
		val existingMember = memberCommandService.getMemberBySnsIdAndSNSType(snsMemberInfo.snsId, snsMemberInfo.snsType)

		val member =
			if (existingMember == null) {
				memberQueryService.saveMember(snsMemberInfo)
			} else {
				existingMember.updateLastLoginAt()
				existingMember
			}

		return member.snsId
	}
}