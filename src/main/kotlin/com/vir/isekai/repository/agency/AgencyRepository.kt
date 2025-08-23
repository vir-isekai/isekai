package com.vir.isekai.repository.agency

import com.vir.isekai.domain.entity.business.Agency
import org.springframework.data.jpa.repository.JpaRepository

interface AgencyRepository : JpaRepository<Agency, Long>