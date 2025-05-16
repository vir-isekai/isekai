package com.vir.isekai.security

import com.vir.isekai.entity.enums.SNSType
import com.vir.isekai.entity.enums.member.MemberRole
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserPrincipal(
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

	fun getId(): Long? = id

	fun getSnsId(): String = snsId

	fun getSnsType(): SNSType = snsType

	fun getEmail(): String? = email

	companion object {
		fun createFromSnsInfo(
			snsId: String,
			snsType: SNSType,
		): UserPrincipal {
			val authorities = listOf(SimpleGrantedAuthority(MemberRole.NORMAL.name))
			
			return UserPrincipal(
				id = null,
				snsId = snsId,
				snsType = snsType,
				email = null,
				authorities = authorities,
			)
		}
	}
}