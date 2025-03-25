package com.vir.isekai.adapter

import com.fasterxml.jackson.annotation.JsonProperty
import com.vir.isekai.port.OAuthPort
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient

@Component("kakaoOAuthAdapter")
class KakaoOAuthAdapter : OAuthPort {
	@Value("\${kakao.client_id}")
	lateinit var clientId: String

	@Value("\${kakao.redirect_uri}")
	lateinit var redirectURI: String

	private val authRestClient: RestClient =
		RestClient.builder()
			.baseUrl(BASE_AUTH_URL)
			.build()

	private val restClient: RestClient =
		RestClient.builder()
			.baseUrl(BASE_API_URL)
			.build()

	override fun getAccessToken(code: String): String {
		val response =
			authRestClient.post()
				.uri("/oauth/token")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.body(
					"grant_type=authorization_code" +
						"&client_id=$clientId" +
						"&redirect_uri=$redirectURI" +
						"&code=$code",
				)
				.retrieve()
				.body(KakaoTokenResponse::class.java) ?: throw IllegalArgumentException("Kakao 통신 에러 발생")

		return response.accessToken
	}

	override fun getSNSMemberInfo(token: String): String {
		val response =
			restClient.get()
				.uri("/v2/user/me")
				.header("Authorization", "Bearer $token")
				.retrieve()
				.body(String::class.java) ?: throw IllegalArgumentException("Kakao 통신 에러 발생")

		return response
	}

	private data class KakaoTokenResponse(
		@field:JsonProperty("access_token")
		val accessToken: String,

		@field:JsonProperty("token_type")
		val tokenType: String,

		@field:JsonProperty("refresh_token")
		val refreshToken: String,

		@field:JsonProperty("expires_in")
		val expiresIn: Int,

		@field:JsonProperty("scope")
		val scope: String? = null,

		@field:JsonProperty("refresh_token_expires_in")
		val refreshTokenExpiresIn: Int? = null,
	)

	data class KakaoUserResponse(
		val id: String,
	)

	companion object {
		private const val BASE_AUTH_URL = "https://kauth.kakao.com"
		private const val BASE_API_URL = "https://kaapi.kakao.com"
	}
}