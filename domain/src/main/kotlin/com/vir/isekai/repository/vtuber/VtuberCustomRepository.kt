package com.vir.isekai.repository.vtuber

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import com.vir.isekai.dto.command.VtuberCommand
import com.vir.isekai.entity.QAgency.agency
import com.vir.isekai.entity.QVtuber.vtuber
import org.springframework.stereotype.Repository

@Repository
class VtuberCustomRepository(
	private val queryFactory: JPAQueryFactory,
) {
	fun getVtuberById(vtuberId: Long): VtuberCommand.Detail? {
		return queryFactory
			.select(
				Projections.constructor(
					VtuberCommand.Detail::class.java,
					vtuber.id,
					vtuber.name,
					vtuber.age,
					vtuber.height,
					vtuber.race,
					vtuber.platform,
				),
			)
			.from(vtuber)
			.where(vtuber.id.eq(vtuberId))
			.fetchOne()
	}

	fun getVtubersByAgencyId(agencyId: Long): List<VtuberCommand.Simple> {
		return queryFactory
			.select(
				Projections.constructor(
					VtuberCommand.Simple::class.java,
					vtuber.id,
					vtuber.name,
				),
			)
			.from(vtuber)
			.join(agency)
			.on(agency.id.eq(vtuber.agency.id))
			.where(agency.id.eq(agencyId))
			.fetch()
	}
}