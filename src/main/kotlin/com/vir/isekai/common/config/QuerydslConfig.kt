package com.vir.isekai.config

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class QuerydslConfig {
	@PersistenceContext(unitName = "main")
	private lateinit var mainEntityManager: EntityManager

	@Bean
	@Primary
	fun mainJpaQueryFactory() = JPAQueryFactory(mainEntityManager)
}