package com.vir.isekai.entity

import jakarta.persistence.*

@Entity
@Table(name = "fandom")
class Fandom(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fandom_id")
	val id: Long? = null,

	@OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
	@JoinColumn(name = "agency_id")
	val agency: Agency? = null,

	@OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
	@JoinColumn(name = "v_tuber_id")
	val vtuber: Vtuber? = null,

	val name: String,

	val logoImageUrl: String,
) : BaseTimeEntity()