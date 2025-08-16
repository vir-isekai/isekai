package com.vir.isekai.repository.artist

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class ArtistCustomRepository(
	private val queryFactory: JPAQueryFactory,
)