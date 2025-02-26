package com.vir.isekai.entity

import com.vir.isekai.entity.enums.Nation
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

	val logoImageUrl: String,

	@Enumerated(EnumType.STRING)
	val nation: Nation,

	val establishedDate: LocalDate,

	val closedDate: LocalDate? = null,
) : BaseTimeEntity()