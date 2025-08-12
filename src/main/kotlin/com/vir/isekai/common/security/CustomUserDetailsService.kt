package com.vir.isekai.common.security

import com.vir.isekai.domain.entity.enums.SNSType
import com.vir.isekai.service.login.auth.AuthenticationService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
	private val authenticationService: AuthenticationService,
) : UserDetailsService {
	// username 형식: "snsType:snsId" (예: "KAKAO:12345678")
	override fun loadUserByUsername(username: String): UserDetails {
		try {
			val (snsType, snsId) = username.split(":")
			
			// 사용자 조회
			val user =
				authenticationService.findUserBySnsIdAndType(snsId, SNSType.KAKAO)
					?: throw UsernameNotFoundException("User not found with snsId: $snsId and snsType: $snsType")
			
			// UserDetails 객체 생성
			return UserPrincipal.createFromSnsInfo(snsId, SNSType.KAKAO)
		} catch (e: Exception) {
			throw UsernameNotFoundException("Invalid username format or user not found: $username")
		}
	}
}