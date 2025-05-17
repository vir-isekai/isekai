package com.vir.isekai.repository.agency

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import com.vir.isekai.dto.response.AgencyResponse
import com.vir.isekai.entity.QAgency.agency
import com.vir.isekai.entity.QVtuber.*
import org.springframework.stereotype.Repository

@Repository
class AgencyCustomRepository(
	private val queryFactory: JPAQueryFactory,
) {
	fun getAgencies(): List<AgencyResponse.Entry> {
		return queryFactory
			.select(
				Projections.constructor(
					AgencyResponse.Entry::class.java,
					agency.id,
					agency.name,
					agency.logoImageUrl,
				),
			)
			.from(agency)
			.fetch()
	}

	fun getAgencyById(agencyId: Long): AgencyResponse.Detail? {
		return queryFactory
			.select(
				Projections.constructor(
					AgencyResponse.Detail::class.java,
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

	fun getAgencyByVtuberId(vtuberId: Long): AgencyResponse.Entry? {
		return queryFactory
			.select(
				Projections.constructor(
					AgencyResponse.Entry::class.java,
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