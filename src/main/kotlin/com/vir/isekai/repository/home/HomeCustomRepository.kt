package com.vir.isekai.repository.home

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import com.vir.isekai.domain.dto.response.HomeResponse
import com.vir.isekai.domain.entity.QAgency.agency
import com.vir.isekai.domain.entity.QArtist.artist
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

	fun getArtistCount(): Long? {
		return queryFactory
			.select(artist.id.count())
			.from(artist)
			.fetchOne()
	}

	fun getArtistsWithAgency(): List<HomeResponse.PopularArtistInfo> {
		return queryFactory
			.select(
				Projections.constructor(
					HomeResponse.PopularArtistInfo::class.java,
					artist.id,
					artist.name,
					artist.profileImageUrl,
					agency.id,
					agency.name,
				),
			)
			.from(artist)
			.join(agency)
			.on(agency.id.eq(artist.agency.id))
			.limit(5)
			.fetch()
	}
}