package com.vir.isekai.domain.entity.business

import com.vir.isekai.domain.entity.BaseTimeEntity
import com.vir.isekai.domain.entity.enums.ChannelType
import jakarta.persistence.*

@Entity
@Table(name = "channel")
class Channel(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "channel_id")
	val id: Long? = null,

	@OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
	@JoinColumn(name = "agency_id")
	var agency: Agency? = null,

	@OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
	@JoinColumn(name = "artist_id")
	var artist: Artist? = null,

	@Enumerated(EnumType.STRING)
	val type: ChannelType,

	val url: String,
) : BaseTimeEntity()