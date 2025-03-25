package com.vir.isekai.repository.channel

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import com.vir.isekai.dto.command.ChannelCommand
import com.vir.isekai.entity.QAgency.agency
import com.vir.isekai.entity.QChannel.channel
import com.vir.isekai.entity.QVtuber.*
import org.springframework.stereotype.Repository

@Repository
class ChannelCustomRepository(
	private val queryFactory: JPAQueryFactory,
) {
	fun getChannelsByAgencyId(agencyId: Long): List<ChannelCommand.Simple> {
		return queryFactory
			.select(
				Projections.constructor(
					ChannelCommand.Simple::class.java,
					channel.type,
					channel.url,
				),
			)
			.from(channel)
			.join(agency)
			.on(agency.id.eq(channel.agency.id))
			.where(agency.id.eq(agencyId))
			.fetch()
	}

	fun getChannelsByVtuberId(vtuberId: Long): List<ChannelCommand.Simple> {
		return queryFactory
			.select(
				Projections.constructor(
					ChannelCommand.Simple::class.java,
					channel.type,
					channel.url,
				),
			)
			.from(channel)
			.join(vtuber)
			.on(vtuber.id.eq(channel.vtuber.id))
			.where(vtuber.id.eq(vtuberId))
			.fetch()
	}
}