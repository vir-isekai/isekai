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

	@OneToOne(mappedBy = "agency", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
	var fandom: Fandom? = null,

	val name: String,

	val logoImageUrl: String,

	@Enumerated(EnumType.STRING)
	val nation: Nation,

	val establishedDate: LocalDate? = null,

	val closedDate: LocalDate? = null,
) : BaseTimeEntity() {
	constructor(
		name: String,
		logoImageUrl: String,
		nation: Nation,
		establishedDate: LocalDate,
		closedDate: LocalDate? = null,
	) : this(
		null,
		null,
		name,
		logoImageUrl,
		nation,
		establishedDate,
		closedDate,
	)
}