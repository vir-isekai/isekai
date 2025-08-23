package com.vir.isekai.repository.channel

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import com.vir.isekai.domain.dto.response.ChannelResponse
import com.vir.isekai.domain.entity.business.QArtist.artist
import com.vir.isekai.domain.entity.business.QChannel.channel
import org.springframework.stereotype.Repository

@Repository
class ChannelCustomRepository(
	private val queryFactory: JPAQueryFactory,
) {
	fun getChannelsByArtistId(artistId: Long): List<ChannelResponse.Simple> {
		return queryFactory
			.select(
				Projections.constructor(
					ChannelResponse.Simple::class.java,
					channel.type,
					channel.url,
				),
			)
			.from(channel)
			.join(artist)
			.on(artist.id.eq(channel.artist.id))
			.where(channel.artist.id.eq(artistId))
			.fetch()
	}
}