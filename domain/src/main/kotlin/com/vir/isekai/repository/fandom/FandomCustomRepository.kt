package com.vir.isekai.repository.fandom

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class FandomCustomRepository(
	private val queryFactory: JPAQueryFactory,
)