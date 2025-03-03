package com.vir.isekai.repository.fandom

import com.vir.isekai.entity.Fandom
import org.springframework.data.jpa.repository.JpaRepository

interface FandomRepository : JpaRepository<Fandom, Long>