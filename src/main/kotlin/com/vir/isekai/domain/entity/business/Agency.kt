package com.vir.isekai.domain.entity.business

import com.vir.isekai.domain.entity.BaseTimeEntity
import com.vir.isekai.domain.entity.enums.Nation
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "agency")
class Agency(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "agency_id")
	val id: Long? = null,

	val name: String,

	@Column(name = "logo_image_url")
	val logoImageUrl: String,

	@Enumerated(EnumType.STRING)
	val nation: Nation = Nation.KOREA,

	val establishedDate: LocalDate? = null,
) : BaseTimeEntity() {
	constructor(
		name: String,
		logoImageUrl: String,
		nation: Nation,
		establishedDate: LocalDate,
	) : this(
		null,
		name,
		logoImageUrl,
		nation,
		establishedDate,
	)
}