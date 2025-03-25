package com.vir.isekai.adapter

import com.fasterxml.jackson.annotation.JsonProperty
import com.vir.isekai.port.OAuthPort
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestClient

@Component("kakaoOAuthAdapter")
class KakaoOAuthAdapter : OAuthPort {
	@Value("\${kakao.client_id}")
	lateinit var clientId: String

	@Value("\${kakao.redirect_uri}")
	lateinit var redirectURI: String

	private val restClient: RestClient =
		RestClient.builder()
			.baseUrl(BASE_URL)
			.build()

	override fun getAccessToken(code: String): String {
		val requestParams: MultiValueMap<String, String> = LinkedMultiValueMap()
		requestParams.add("grant_type", "authorization_code")
		requestParams.add("code", code)
		requestParams.add("client_id", clientId)
		requestParams.add("redirect_uri", redirectURI)

		val response =
			restClient.post()
				.uri("/oauth/token")
				.contentType(MediaType.APPLICATION_JSON)
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

	companion object {
		private const val BASE_URL = "https://kauth.kakao.com"
	}
}