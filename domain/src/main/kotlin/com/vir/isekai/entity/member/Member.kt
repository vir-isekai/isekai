package com.vir.isekai.entity.member

import com.vir.isekai.entity.BaseTimeEntity
import com.vir.isekai.entity.enums.MemberRole
import jakarta.persistence.*

@Entity
@Table(name = "member")
class Member(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	val id: Long? = null,

	@Enumerated(EnumType.STRING)
	val role: MemberRole,
) : BaseTimeEntity()