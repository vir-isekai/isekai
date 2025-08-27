package com.vir.isekai.adapter

import com.fasterxml.jackson.annotation.JsonProperty
import com.vir.isekai.domain.dto.response.MemberResponse
import com.vir.isekai.domain.entity.enums.member.SNSType
import com.vir.isekai.port.AuthPort
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient

@Component
class GoogleAuthAdapter : AuthPort {
	@Value("\${google.client-id}")
	lateinit var clientId: String

	@Value("\${google.client-secret}")
	lateinit var clientSecret: String

	@Value("\${google.redirect-uri}")
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
				.uri("/token")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.body(
					"grant_type=authorization_code" +
						"&client_id=$clientId" +
						"&client_secret=$clientSecret" +
						"&redirect_uri=$redirectURI" +
						"&code=$code",
				)
				.retrieve()
				.body(GoogleTokenResponse::class.java) ?: throw IllegalArgumentException("Google 통신 에러 발생")

		return response.accessToken
	}

	override fun getMemberSaveDTO(token: String): MemberResponse.Save {
		val response =
			restClient.get()
				.uri("/userinfo")
				.header("Authorization", "Bearer $token")
				.retrieve()
				.body(GoogleUserResponse::class.java) ?: throw IllegalArgumentException("Google 통신 에러 발생")

		return response.toMemberSaveDTO()
	}

	private data class GoogleTokenResponse(
		@field:JsonProperty("access_token")
		val accessToken: String,

		@field:JsonProperty("token_type")
		val tokenType: String,

		@field:JsonProperty("refresh_token")
		val refreshToken: String? = null,

		@field:JsonProperty("expires_in")
		val expiresIn: Int,

		@field:JsonProperty("scope")
		val scope: String? = null,
	)

	private data class GoogleUserResponse(
		val id: String,
		val name: String,
		val email: String? = null,
		val picture: String? = null,
	) {
		fun toMemberSaveDTO(): MemberResponse.Save {
			return MemberResponse.Save(
				snsId = id,
				nickname = name,
				snsType = SNSType.GOOGLE,
			)
		}
	}

	companion object {
		private const val BASE_AUTH_URL = "https://oauth2.googleapis.com"
		private const val BASE_API_URL = "https://www.googleapis.com/oauth2/v1"
	}
}