package com.vir.isekai.domain.entity.post

import com.vir.isekai.domain.entity.BaseTimeEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "post")
class Post(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	val id: Long? = null,
	
	val title: String,
	
	val content: String,
) : BaseTimeEntity()