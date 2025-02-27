package com.vir.isekai.dto

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.servlet.http.HttpServletResponse
import jakarta.servlet.http.HttpServletResponse.SC_CREATED
import jakarta.servlet.http.HttpServletResponse.SC_OK

data class CommonResponse<T>(
	@JsonInclude(JsonInclude.Include.NON_NULL)
	val response: T?,
	val status: Int,
) {
	constructor(status: Int) : this(null, status)

	companion object {
		fun <T> ok(): CommonResponse<T> {
			return CommonResponse(null, SC_OK)
		}

		fun <T> successSave(): CommonResponse<T> {
			return CommonResponse(SC_CREATED)
		}

		fun <T> ok(status: HttpServletResponse): CommonResponse<T> {
			return CommonResponse(null, status.status)
		}

		fun <T> ok(response: T): CommonResponse<T> {
			return CommonResponse(response, SC_OK)
		}
	}
}