package com.vir.isekai.common.security

import com.vir.isekai.domain.entity.enums.SNSType
import com.vir.isekai.domain.entity.member.Member
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class UserPrincipal(
	private val id: Long?,
	private val snsId: String,
	private val snsType: SNSType,
	private val email: String?,
	private val authorities: Collection<GrantedAuthority>,
) : UserDetails {
	override fun getAuthorities(): Collection<GrantedAuthority> = authorities

	override fun getPassword(): String? = null // OAuth 기반 인증이므로 비밀번호 없음

	override fun getUsername(): String = "$snsType:$snsId" // 고유 식별자로 조합

	override fun isAccountNonExpired(): Boolean = true

	override fun isAccountNonLocked(): Boolean = true

	override fun isCredentialsNonExpired(): Boolean = true

	override fun isEnabled(): Boolean = true

	fun getId(): Long = id ?: throw IllegalStateException("UserPrincipal ID Should Not be NULL")

	fun getSnsId(): String = snsId

	fun getSnsType(): SNSType = snsType

	fun getEmail(): String? = email

	companion object {
		fun createFromMember(member: Member): UserPrincipal {
			// Member의 실제 role을 사용하여 권한 생성
			val authorities = listOf(SimpleGrantedAuthority("ROLE_${member.role.name}"))
			
			return UserPrincipal(
				id = member.id,
				snsId = member.snsId,
				snsType = member.snsType,
				email = null,
				authorities = authorities,
			)
		}
	}
}