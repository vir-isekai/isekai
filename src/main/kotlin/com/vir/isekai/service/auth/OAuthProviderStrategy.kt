package com.vir.isekai.service.auth

import com.vir.isekai.adapter.GoogleAuthAdapter
import com.vir.isekai.adapter.KakaoAuthAdapter
import com.vir.isekai.domain.dto.response.MemberResponse
import com.vir.isekai.domain.entity.enums.member.SNSType
import com.vir.isekai.port.AuthPort
import org.springframework.stereotype.Component

@Component
class OAuthProviderStrategy(
	private val kakaoAuthAdapter: KakaoAuthAdapter,
	private val googleAuthAdapter: GoogleAuthAdapter,
) {
	fun getAuthAdapter(provider: SNSType): AuthPort {
		return when (provider) {
			SNSType.KAKAO -> kakaoAuthAdapter
			SNSType.GOOGLE -> googleAuthAdapter
			SNSType.APPLE -> throw UnsupportedOperationException("Apple OAuth는 아직 지원하지 않습니다")
			SNSType.NAVER -> throw UnsupportedOperationException("Naver OAuth는 아직 지원하지 않습니다")
		}
	}

	fun authenticateUser(
		provider: SNSType,
		code: String,
	): MemberResponse.Save {
		val authAdapter = getAuthAdapter(provider)
		val accessToken = authAdapter.getAccessToken(code)
		
		return authAdapter.getMemberSaveDTO(accessToken)
	}
}