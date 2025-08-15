package com.vir.isekai.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/test")
class TestController {
	@GetMapping
	fun publicEndpoint(): ResponseEntity<Map<String, String>> {
		return ResponseEntity.ok(mapOf("message" to "정상 호출 200"))
	}
}