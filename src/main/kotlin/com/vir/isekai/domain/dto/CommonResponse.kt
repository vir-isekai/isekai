package com.vir.isekai.domain.dto

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.servlet.http.HttpServletResponse.*

data class CommonResponse<T>(
	@param:JsonInclude(JsonInclude.Include.NON_NULL)
	val response: T?,
	val status: Int,
) {
	constructor(status: Int) : this(null, status)

	companion object {
		fun <T> successSave(): CommonResponse<T> {
			return CommonResponse(SC_CREATED)
		}

		fun <T> noContent(): CommonResponse<T> {
			return CommonResponse(SC_NO_CONTENT)
		}

		fun <T> ok(response: T): CommonResponse<T> {
			return CommonResponse(response, SC_OK)
		}

		fun <T> error(message: String): CommonResponse<T> {
			return CommonResponse(SC_INTERNAL_SERVER_ERROR)
		}
	}
}