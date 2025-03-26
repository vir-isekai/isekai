package com.vir.isekai.dto

import com.vir.isekai.entity.enums.SNSType
import com.vir.isekai.entity.enums.member.MemberStatus
import com.vir.isekai.entity.member.Member
import java.time.LocalDateTime

class MemberDTO {
	data class Save(
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
}