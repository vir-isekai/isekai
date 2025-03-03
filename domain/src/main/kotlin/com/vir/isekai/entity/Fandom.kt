package com.vir.isekai.entity

import jakarta.persistence.*

@Entity
@Table(name = "fandom")
class Fandom(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fandom_id")
	val id: Long? = null,

	val name: String,

	val logoImageUrl: String,
)