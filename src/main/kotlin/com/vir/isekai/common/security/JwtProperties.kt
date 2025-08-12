package com.vir.isekai.common.security

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "jwt")
class JwtProperties {
	lateinit var secretKey: String
	lateinit var issuer: String
	var accessTokenExpirationMs: Long = 1800000 // 30분 기본값
	var refreshTokenExpirationMs: Long = 604800000 // 7일 기본값
}