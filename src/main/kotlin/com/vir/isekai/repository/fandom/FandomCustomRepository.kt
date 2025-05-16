package com.vir.isekai.repository.fandom

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import com.vir.isekai.dto.command.FandomCommand
import com.vir.isekai.entity.QAgency.agency
import com.vir.isekai.entity.QFandom.fandom
import com.vir.isekai.entity.QVtuber.vtuber
import org.springframework.stereotype.Repository

@Repository
class FandomCustomRepository(
	private val queryFactory: JPAQueryFactory,
) {
	fun getFandomByAgencyId(agencyId: Long): FandomCommand.Simple? {
		return queryFactory
			.select(
				Projections.constructor(
					FandomCommand.Simple::class.java,
					fandom.id,
					fandom.name,
					fandom.logoImageUrl,
				),
			)
			.from(fandom)
			.join(agency)
			.on(agency.id.eq(fandom.agency.id))
			.where(fandom.agency.id.eq(agencyId))
			.fetchOne()
	}

	fun getFandomByVtuberId(vtuberId: Long): FandomCommand.Simple? {
		return queryFactory
			.select(
				Projections.constructor(
					FandomCommand.Simple::class.java,
					fandom.id,
					fandom.name,
					fandom.logoImageUrl,
				),
			)
			.from(fandom)
			.join(vtuber)
			.on(vtuber.id.eq(fandom.vtuber.id))
			.where(fandom.vtuber.id.eq(vtuberId))
			.fetchOne()
	}
}