package com.vir.isekai.domain.dto

// 인증 응답 DTO
data class AuthResponse(
	val accessToken: String,
	val tokenType: String = "Bearer",
)

// 토큰 갱신 응답 DTO
data class TokenRefreshResponse(
	val accessToken: String,
	val tokenType: String = "Bearer",
)

// 카카오 사용자 정보 DTO
data class KakaoUserInfo(
	val id: String,
	val properties: Map<String, String>?,
	val kakaoAccount: KakaoAccount?,
)

data class KakaoAccount(
	val email: String?,
	val profile: KakaoProfile?,
)

data class KakaoProfile(
	val nickname: String?,
	val profileImageUrl: String?,
)