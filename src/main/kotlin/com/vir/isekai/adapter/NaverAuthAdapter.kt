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
class NaverAuthAdapter : AuthPort {
	@Value("\${naver.client-id}")
	lateinit var clientId: String

	@Value("\${naver.client-secret}")
	lateinit var clientSecret: String

	@Value("\${naver.redirect-uri}")
	lateinit var redirectURI: String

	private val authRestClient: RestClient =
		RestClient.builder()
			.baseUrl(BASE_TOKEN_URI)
			.build()

	private val restClient: RestClient =
		RestClient.builder()
			.baseUrl(BASE_API_URL)
			.build()

	override fun getAccessToken(code: String): String {
		val response =
			authRestClient.post()
				.uri("/oauth2.0/token")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.body(
					"grant_type=authorization_code" +
						"&client_id=$clientId" +
						"&client_secret=$clientSecret" +
						"&redirect_uri=$redirectURI" +
						"&code=$code",
				)
				.retrieve()
				.body(NaverTokenResponse::class.java) ?: throw IllegalArgumentException("Naver 통신 에러 발생")

		return response.accessToken
	}

	override fun getMemberSaveDTO(token: String): MemberResponse.Save {
		val response =
			restClient.get()
				.uri("/v1/nid/me")
				.header("Authorization", "Bearer $token")
				.retrieve()
				.body(NaverUserResponse::class.java) ?: throw IllegalArgumentException("Naver 통신 에러 발생")

		return response.toMemberSaveDTO()
	}

	private data class NaverTokenResponse(
		@field:JsonProperty("access_token")
		val accessToken: String,

		@field:JsonProperty("token_type")
		val tokenType: String,

		@field:JsonProperty("refresh_token")
		val refreshToken: String,

		@field:JsonProperty("expires_in")
		val expiresIn: Int,
	)

	private data class NaverUserResponse(
		@field:JsonProperty("resultcode")
		val resultCode: String,
		val message: String,
		val response: NaverUserInfo,
	) {
		fun toMemberSaveDTO(): MemberResponse.Save {
			return MemberResponse.Save(
				snsId = response.id,
				nickname = response.nickname,
				snsType = SNSType.NAVER,
			)
		}
	}

	private data class NaverUserInfo(
		val id: String,
		val nickname: String,
		val email: String? = null,
		@field:JsonProperty("profile_image")
		val profileImage: String? = null,
	)

	companion object {
		private const val BASE_TOKEN_URI = "https://nid.naver.com"
		private const val BASE_API_URL = "https://openapi.naver.com"
	}
}