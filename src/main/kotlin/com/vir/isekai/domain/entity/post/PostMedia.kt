package com.vir.isekai.domain.entity.post

import com.vir.isekai.domain.entity.BaseTimeEntity
import com.vir.isekai.domain.entity.enums.MediaType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "post_media")
class PostMedia(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_media_id")
	val id: Long? = null,

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	val post: Post,

	@Enumerated(EnumType.STRING)
	@Column(name = "media_type")
	val mediaType: MediaType,

	val contentType: String,
	
	@Column(name = "original_file_name")
	val originalFileName: String,

	@Column(name = "original_url")
	val originalUrl: String,

	@Column(name = "thumbnail_url")
	val thumbnailUrl: String,

	@Column(name = "display_order")
	val displayOrder: Int = 0,
) : BaseTimeEntity()