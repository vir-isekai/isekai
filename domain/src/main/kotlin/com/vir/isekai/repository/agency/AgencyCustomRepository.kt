package com.vir.isekai.repository.agency

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import com.vir.isekai.dto.command.AgencyCommand
import com.vir.isekai.entity.QAgency.agency
import org.springframework.stereotype.Repository

@Repository
class AgencyCustomRepository(
	private val queryFactory: JPAQueryFactory,
) {
	fun getAgencies(): List<AgencyCommand.Simple> {
		return queryFactory
			.select(
				Projections.constructor(
					AgencyCommand.Simple::class.java,
					agency.id,
					agency.name,
					agency.logoImageUrl,
				),
			)
			.from(agency)
			.fetch()
	}

	fun getAgencyById(agencyId: Long): AgencyCommand.Detail? {
		return queryFactory
			.select(
				Projections.constructor(
					AgencyCommand.Detail::class.java,
					agency.id,
					agency.name,
					agency.logoImageUrl,
					agency.nation,
					agency.establishedDate,
					agency.closedDate,
				),
			)
			.from(agency)
			.where(agency.id.eq(agencyId))
			.fetchOne()
	}

	fun getAgencyCount(): Long? {
		return queryFactory
			.select(agency.id.count())
			.from(agency)
			.fetchOne()
	}
}