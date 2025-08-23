package com.vir.isekai.domain.entity.member

import com.vir.isekai.domain.entity.BaseTimeEntity
import com.vir.isekai.domain.entity.business.Agency
import com.vir.isekai.domain.entity.business.Artist
import com.vir.isekai.domain.entity.enums.member.BusinessType
import jakarta.persistence.*

@Entity
class BusinessProfile(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "business_profile_id")
	val id: Long? = null,

	@OneToOne(fetch = FetchType.LAZY, cascade = [(CascadeType.REMOVE)])
	@JoinColumn(name = "member_id")
	val member: Member,

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "agency_id")
	val agency: Agency?,

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "artist_id")
	val artist: Artist?,

	@Enumerated(EnumType.STRING)
	val type: BusinessType,
) : BaseTimeEntity() {
	init {
		require(
			(type == BusinessType.AGENCY && agency != null && artist == null) ||
				(type == BusinessType.ARTIST && artist != null && agency == null),
		) { "BusinessProfile must have exactly one business entity matching the type" }
	}
}