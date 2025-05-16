package com.vir.isekai.entity

import com.vir.isekai.entity.enums.ChannelType
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
	@JoinColumn(name = "v_tuber_id")
	var vtuber: Vtuber? = null,

	@Enumerated(EnumType.STRING)
	val type: ChannelType,

	val url: String,
) : BaseTimeEntity()