package com.vir.isekai.domain.entity.member

import com.vir.isekai.domain.entity.BaseTimeEntity
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

	@Column(name = "agency_id")
	val agencyId: Long? = null,

	@Column(name = "artist_id")
	val artistId: Long? = null,

	@Enumerated(EnumType.STRING)
	val type: BusinessType,
) : BaseTimeEntity()