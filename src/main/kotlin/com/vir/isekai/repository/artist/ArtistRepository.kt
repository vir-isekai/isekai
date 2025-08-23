package com.vir.isekai.repository.artist

import com.vir.isekai.domain.entity.business.Artist
import org.springframework.data.jpa.repository.JpaRepository

interface ArtistRepository : JpaRepository<Artist, Long> {
	fun findArtistById(artistId: Long): Artist?
}