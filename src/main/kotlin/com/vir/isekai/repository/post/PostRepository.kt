package com.vir.isekai.repository.post

import com.vir.isekai.domain.entity.post.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long>