package com.vir.isekai.common.config

import org.jasypt.encryption.StringEncryptor
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JasyptConfig {
	@Value("\${jasypt.encryptor.password}")
	private val secret: String? = null

	@Bean("jasyptStringEncryptor")
	fun stringEncryptor(): StringEncryptor {
		val config =
			SimpleStringPBEConfig().apply {
				password = secret
				algorithm = ALGORITHM
				keyObtentionIterations = KEY_OBTENTION_ITERATIONS
				poolSize = POOL_SIZE
				providerName = PROVIDE_NAME
				setSaltGeneratorClassName(RANDOM_SALT_GENERATOR_CLASS_NAME)
				setIvGeneratorClassName(IV_GENERATOR_CLASS_NAME)
				stringOutputType = STRING_OUTPUT_TYPE
			}

		return PooledPBEStringEncryptor().apply { setConfig(config) }
	}

	companion object {
		private const val ALGORITHM = "PBEWithMD5AndDES"
		private const val KEY_OBTENTION_ITERATIONS = 1000
		private const val POOL_SIZE = 1
		private const val PROVIDE_NAME = "SunJCE"
		private const val RANDOM_SALT_GENERATOR_CLASS_NAME = "org.jasypt.salt.RandomSaltGenerator"
		private const val IV_GENERATOR_CLASS_NAME = "org.jasypt.iv.NoIvGenerator"
		private const val STRING_OUTPUT_TYPE = "base64"
	}
}