package com.vir.isekai.domain.entity

import com.vir.isekai.domain.entity.enums.Generation
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "v_tuber")
class Vtuber(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "v_tuber_id")
	val id: Long? = null,

	@OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
	@JoinColumn(name = "agency_id")
	var agency: Agency? = null,

	@OneToOne(mappedBy = "vtuber", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
	var fandom: Fandom? = null,

	val name: String,

	val profileImageUrl: String,

	val age: Int,

	val height: Int,

	@Enumerated(EnumType.STRING)
	val generation: Generation,

	val race: String,

	val debutDate: LocalDate? = null,

	val graduateDate: LocalDate? = null,
) : BaseTimeEntity()