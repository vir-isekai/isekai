package com.vir.isekai.repository.channel

import com.vir.isekai.domain.entity.Channel
import org.springframework.data.jpa.repository.JpaRepository

interface ChannelRepository : JpaRepository<Channel, Long>