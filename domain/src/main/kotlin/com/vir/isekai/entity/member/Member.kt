package com.vir.isekai.entity.member

import com.vir.isekai.entity.BaseTimeEntity
import com.vir.isekai.entity.enums.SNSType
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "member")
class Member(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	val id: Long? = null,

	@Enumerated(EnumType.STRING)
	val snsType: SNSType,

	val snsId: String,

	val nickname: String,

	val profileImageUrl: String,

	val isActive: Boolean,

	val lastLoginAt: LocalDateTime?,
) : BaseTimeEntity()