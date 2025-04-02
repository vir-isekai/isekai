package com.vir.isekai.dto

data class AuthRequest(
	val code: String,
)

// 인증 응답 DTO
data class AuthResponse(
	val accessToken: String,
)

// 토큰 갱신 응답 DTO
data class TokenRefreshResponse(
	val refreshToken: String,
)