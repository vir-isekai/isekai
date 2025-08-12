package com.vir.isekai.repository.vtuber

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class VtuberCustomRepository(
	private val queryFactory: JPAQueryFactory,
)