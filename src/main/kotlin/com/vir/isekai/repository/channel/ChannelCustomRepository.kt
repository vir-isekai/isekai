package com.vir.isekai.repository.channel

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class ChannelCustomRepository(
	private val queryFactory: JPAQueryFactory,
)