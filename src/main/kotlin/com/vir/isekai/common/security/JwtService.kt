package com.vir.isekai.common.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.util.*
import javax.crypto.SecretKey

@Service
class JwtService(private val jwtProperties: JwtProperties) {
	private val secretKey: SecretKey by lazy {
		Keys.hmacShaKeyFor(jwtProperties.secretKey.toByteArray(StandardCharsets.UTF_8))
	}

	fun generateToken(userDetails: UserDetails): String {
		val now = Date()
		val expirationDate = Date(now.time + jwtProperties.accessTokenExpirationMs)

		return Jwts.builder()
			.setSubject(userDetails.username)
			.setIssuedAt(now)
			.setExpiration(expirationDate)
			.setIssuer(jwtProperties.issuer)
			.signWith(secretKey, SignatureAlgorithm.HS256)
			.compact()
	}

	fun generateRefreshToken(userDetails: UserDetails): String {
		val now = Date()
		val expirationDate = Date(now.time + jwtProperties.refreshTokenExpirationMs)

		return Jwts.builder()
			.setSubject(userDetails.username)
			.setIssuedAt(now)
			.setExpiration(expirationDate)
			.setIssuer(jwtProperties.issuer)
			.claim("tokenType", "refresh")
			.signWith(secretKey, SignatureAlgorithm.HS256)
			.compact()
	}

	fun extractUsername(token: String): String? {
		return extractAllClaims(token).subject
	}

	fun isValidToken(
		token: String,
		userDetails: UserDetails,
	): Boolean {
		val username = extractUsername(token)

		return username == userDetails.username && !isExpiredToken(token)
	}

	fun isExpiredToken(token: String): Boolean {
		return extractExpiration(token).before(Date())
	}

	private fun extractAllClaims(token: String): Claims {
		return Jwts.parserBuilder()
			.setSigningKey(secretKey)
			.build()
			.parseClaimsJws(token)
			.body
	}

	private fun extractExpiration(token: String): Date {
		return extractAllClaims(token).expiration
	}
}