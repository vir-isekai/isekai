package com.vir.isekai.repository.home

import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import com.vir.isekai.dto.command.HomeCommand
import com.vir.isekai.entity.QAgency.agency
import com.vir.isekai.entity.QVtuber.vtuber
import org.springframework.stereotype.Repository

/**
 * Home 화면 용도의 Repository
 */
@Repository
class HomeCustomRepository(
	private val queryFactory: JPAQueryFactory,
) {
	fun getAgencyCount(): Long? {
		return queryFactory
			.select(agency.id.count())
			.from(agency)
			.fetchOne()
	}

	fun getVtuberCount(): Long? {
		return queryFactory
			.select(vtuber.id.count())
			.from(vtuber)
			.where(isActiveVtuber())
			.fetchOne()
	}

	fun getVtubersWithAgency(): List<HomeCommand.PopularVtuberInfo> {
		return queryFactory
			.select(
				Projections.constructor(
					HomeCommand.PopularVtuberInfo::class.java,
					vtuber.id,
					vtuber.name,
					vtuber.profileImageUrl,
					agency.id,
					agency.name,
				),
			)
			.from(vtuber)
			.join(agency)
			.on(agency.id.eq(vtuber.agency.id))
			.where(isActiveVtuber())
			.limit(5)
			.fetch()
	}

	private fun isActiveVtuber(): BooleanExpression? = vtuber.graduateDate.isNull
}