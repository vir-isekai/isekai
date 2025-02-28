package com.vir.isekai.repository.agency

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class AgencyCustomRepository(
	private val queryFactory: JPAQueryFactory,
)