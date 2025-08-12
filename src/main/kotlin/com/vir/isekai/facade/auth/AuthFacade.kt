package com.vir.isekai.facade.auth

import com.vir.isekai.service.login.auth.AuthClient
import com.vir.isekai.service.member.MemberCommandService
import com.vir.isekai.service.member.MemberQueryService
import org.springframework.stereotype.Component

@Component
class AuthFacade(
	private val authClient: AuthClient,
	private val memberCommandService: MemberCommandService,
	private val memberQueryService: MemberQueryService,
) {
	/**
	 * 추후 클라이언트에서 Token 받아오게 변경
	 *
	 * 서버에선 토큰 통해 해당 유저 정보만 처리
	 *
	 * 로그인 -> 기존 유저 존재 여부 확인 -> 회원가입 or 로그인 진행
	 */
	fun joinMemberOrLogin(code: String): Any {
		val snsMemberInfo = authClient.getMemberSaveDTO(code)
		val existingMember = memberCommandService.getMemberBySnsIdAndSNSType(snsMemberInfo.snsId, snsMemberInfo.snsType)

		val member =
			if (existingMember == null) {
// 				memberQueryService.saveMember(snsMemberInfo)
			} else {
				existingMember.updateLastLoginAt()
				existingMember
			}

		return 0L
// 		return member.snsId
	}
}