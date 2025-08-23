package com.vir.isekai.repository.agency

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import com.vir.isekai.domain.dto.response.AgencyResponse
import com.vir.isekai.domain.entity.QAgency.agency
import com.vir.isekai.domain.entity.QArtist.*
import org.springframework.stereotype.Repository

@Repository
class AgencyCustomRepository(
	private val queryFactory: JPAQueryFactory,
) {
	fun getAgencies(): List<AgencyResponse.Simple> {
		return queryFactory
			.select(
				Projections.constructor(
					AgencyResponse.Simple::class.java,
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
				),
			)
			.from(agency)
			.where(agency.id.eq(agencyId))
			.fetchOne()
	}

	fun getAgencyByArtistId(artistId: Long): AgencyResponse.Simple? {
		return queryFactory
			.select(
				Projections.constructor(
					AgencyResponse.Simple::class.java,
					agency.id,
					agency.name,
					agency.logoImageUrl,
				),
			)
			.from(agency)
			.join(artist)
			.on(artist.agency.id.eq(agency.id))
			.where(artist.id.eq(artistId))
			.fetchOne()
	}
}