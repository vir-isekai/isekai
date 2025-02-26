package com.vir.isekai.entity.member

import com.vir.isekai.entity.Agency
import com.vir.isekai.entity.BaseTimeEntity
import com.vir.isekai.entity.enums.RaceType
import jakarta.persistence.*

@Entity
@Table(name = "v_tuber")
class VTuber(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "v_tuber_id")
	val id: Long? = null,

	@OneToOne(cascade = [CascadeType.REMOVE])
	@JoinColumn(name = "agency_id")
	val agency: Agency,

	val name: String,

	val age: Int,

	val height: Int,

	val fandomName: String? = null,

	@Enumerated(EnumType.STRING)
	val race: RaceType,
) : BaseTimeEntity()