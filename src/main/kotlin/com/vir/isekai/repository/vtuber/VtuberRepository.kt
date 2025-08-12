package com.vir.isekai.repository.vtuber

import com.vir.isekai.domain.entity.Vtuber
import org.springframework.data.jpa.repository.JpaRepository

interface VtuberRepository : JpaRepository<Vtuber, Long>