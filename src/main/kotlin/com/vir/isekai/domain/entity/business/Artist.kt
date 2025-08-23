package com.vir.isekai.domain.entity.business

import com.vir.isekai.domain.entity.BaseTimeEntity
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "artist")
class Artist(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "artist_id")
	val id: Long? = null,

	@OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
	@JoinColumn(name = "agency_id")
	var agency: Agency? = null,

	val name: String,

	val profileImageUrl: String,

	val debutDate: LocalDate? = null,
) : BaseTimeEntity()