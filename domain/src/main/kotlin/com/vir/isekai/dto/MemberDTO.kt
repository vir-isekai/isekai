package com.vir.isekai.dto

import com.vir.isekai.entity.enums.SNSType
import com.vir.isekai.entity.enums.member.Gender
import com.vir.isekai.entity.enums.member.MemberStatus
import com.vir.isekai.entity.member.Member
import java.time.LocalDateTime

data class MemberSaveDTO(
	val nickname: String,
	val snsType: SNSType,
	val snsId: String,
) {
	fun toEntity(): Member {
		return Member(
			null,
			snsType,
			snsId,
			nickname,
			"",
			MemberStatus.ACTIVE,
			LocalDateTime.now(),
		)
	}
}

data class MemberUpdateDTO(
	val memberId: Long,
)

data class MemberAdditionalInfoSaveDTO(
	val nickname: String,
	val gender: Gender,
	val age: Int,
)