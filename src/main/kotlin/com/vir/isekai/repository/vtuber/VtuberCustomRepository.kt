package com.vir.isekai.repository.vtuber

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import com.vir.isekai.domain.dto.command.VtuberCommand
import com.vir.isekai.domain.entity.QAgency.agency
import com.vir.isekai.domain.entity.QVtuber.vtuber
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
					vtuber.profileImageUrl,
					vtuber.age,
					vtuber.height,
					vtuber.race,
					vtuber.debutDate,
					vtuber.graduateDate,
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
					vtuber.profileImageUrl,
				),
			)
			.from(vtuber)
			.join(agency)
			.on(agency.id.eq(vtuber.agency.id))
			.where(agency.id.eq(agencyId))
			.fetch()
	}
}