package com.vir.isekai.domain.entity.post

import com.vir.isekai.domain.entity.BaseTimeEntity
import com.vir.isekai.domain.entity.member.Member
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "post")
class Post(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	val id: Long? = null,
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	val member: Member,
	
	@OneToMany(mappedBy = "post", cascade = [CascadeType.ALL], orphanRemoval = true)
	val mediaList: List<PostMedia> = mutableListOf(),
	
	val title: String,
	
	val content: String,
) : BaseTimeEntity()