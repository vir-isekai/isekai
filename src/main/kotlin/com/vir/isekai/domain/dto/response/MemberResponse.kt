package com.vir.isekai.domain.dto.response

import com.vir.isekai.domain.entity.enums.member.MemberRole
import com.vir.isekai.domain.entity.enums.member.MemberStatus
import com.vir.isekai.domain.entity.enums.member.SNSType
import com.vir.isekai.domain.entity.member.Member
import java.time.LocalDateTime

class MemberResponse {
	data class Save(
		val snsId: String,
		val nickname: String,
		val snsType: SNSType,
	) {
		fun toEntity(): Member {
			return Member(
				null,
				MemberRole.NORMAL,
				nickname,
				snsType,
				snsId,
				"profileImageUrl",
				MemberStatus.ACTIVE,
				LocalDateTime.now(),
			)
		}
	}
}