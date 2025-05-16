package com.vir.isekai.repository.agency

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import com.vir.isekai.dto.command.AgencyCommand
import com.vir.isekai.entity.QAgency.agency
import com.vir.isekai.entity.QVtuber.*
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

	fun getAgencyByVtuberId(vtuberId: Long): AgencyCommand.Simple? {
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
			.join(vtuber)
			.on(vtuber.agency.id.eq(agency.id))
			.where(vtuber.id.eq(vtuberId))
			.fetchOne()
	}
}