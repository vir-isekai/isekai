package com.vir.isekai.entity

import com.vir.isekai.entity.enums.Generation
import com.vir.isekai.entity.enums.Platform
import com.vir.isekai.entity.enums.Race
import jakarta.persistence.*

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

	val age: Int,

	val height: Int,

	@Enumerated(EnumType.STRING)
	val generation: Generation,

	@Enumerated(EnumType.STRING)
	val race: Race,

	@Enumerated(EnumType.STRING)
	val platform: Platform,
) : BaseTimeEntity()