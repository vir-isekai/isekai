package com.vir.isekai.domain.entity

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
	var agency: Agency? = null,

	@OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
	@JoinColumn(name = "v_tuber_id")
	var vtuber: Vtuber? = null,

	val name: String,

	val logoImageUrl: String,
) : BaseTimeEntity() {
	fun linkAgency(agency: Agency) {
		this.agency = agency
		agency.fandom = this
	}

	fun linkVtuber(vtuber: Vtuber) {
		this.vtuber = vtuber
		vtuber.fandom = this
	}
}