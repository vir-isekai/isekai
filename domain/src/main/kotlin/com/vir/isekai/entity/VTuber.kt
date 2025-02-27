package com.vir.isekai.entity

import com.vir.isekai.entity.enums.Platform
import com.vir.isekai.entity.enums.RaceType
import jakarta.persistence.*

@Entity
@Table(name = "v_tuber")
class VTuber(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "v_tuber_id")
	val id: Long? = null,

// 	@ManyToOne(fetch = FetchType.LAZY)
// 	@JoinColumn(name = "member_id")
// 	val member: Member,

	@OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
	@JoinColumn(name = "agency_id")
	val agency: Agency? = null,

	val name: String,

	val age: Int,

	val height: Int,

	val fandom: String? = null,

	@Enumerated(EnumType.STRING)
	val race: RaceType,

	@Enumerated(EnumType.STRING)
	val platform: Platform,
) : BaseTimeEntity()