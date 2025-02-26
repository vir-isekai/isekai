package com.vir.isekai.entity.member

import com.vir.isekai.entity.Agency
import jakarta.persistence.*

@Entity
@Table(name = "v_tuber")
class VTuber(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "v_tuber_id")
	val id: Long? = null,

	@OneToOne(cascade = [CascadeType.DETACH])
	@JoinColumn(name = "agency_id")
	val agency: Agency,

	val name: String,
)