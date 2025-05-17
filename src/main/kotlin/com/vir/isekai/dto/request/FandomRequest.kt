package com.vir.isekai.dto.request

class FandomRequest {
	data class Save(
		val agencyId: Long?,
		val vtuberId: Long?,
		val name: String,
		val logoImageUrl: String,
	) {
		fun toEntityWithAgency() {
			
		}

		fun toEntityWithVtuber() {

		}
	}
}