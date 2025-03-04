package com.vir.isekai.repository.fandom

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import com.vir.isekai.dto.command.FandomCommand
import com.vir.isekai.entity.QAgency.*
import com.vir.isekai.entity.QFandom.fandom
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
				),
			)
			.from(fandom)
			.join(agency)
			.on(agency.id.eq(fandom.agency.id))
			.where(fandom.agency.id.eq(agencyId))
			.fetchOne()
	}
}