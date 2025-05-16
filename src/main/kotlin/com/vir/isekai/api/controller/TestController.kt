package com.vir.isekai.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/test")
class TestController {
	@GetMapping("/public")
	fun publicEndpoint(): ResponseEntity<Map<String, String>> {
		return ResponseEntity.ok(mapOf("message" to "This is a public endpoint, accessible to all"))
	}

	@GetMapping("/secured")
	fun securedEndpoint(): ResponseEntity<Map<String, String>> {
		return ResponseEntity.ok(
			mapOf("message" to "This is a secured endpoint, but currently accessible to all as per configuration"),
		)
	}
}