package com.vir.isekai.repository.channel

import com.vir.isekai.domain.entity.business.Channel
import org.springframework.data.jpa.repository.JpaRepository

interface ChannelRepository : JpaRepository<Channel, Long>