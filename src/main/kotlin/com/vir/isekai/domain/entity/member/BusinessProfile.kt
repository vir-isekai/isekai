package com.vir.isekai.domain.entity.member

import com.vir.isekai.domain.entity.enums.member.BusinessType
import jakarta.persistence.*

@Entity
class BusinessProfile(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "business_profile_id")
	val id: Long? = null,

	@OneToOne(fetch = FetchType.LAZY, cascade = [(CascadeType.REMOVE)])
	val member: Member,

	val businessType: BusinessType,

	val businessName: String,

	val logoImageUrl: String,
)