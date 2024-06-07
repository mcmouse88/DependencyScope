package com.mcmouse88.koinscopeapp.features.post.domain.repository

import com.mcmouse88.koinscopeapp.features.post.domain.models.Post

interface PostRepository {
    suspend fun getPostList(): List<Post>
    suspend fun getPostById(id: Long): Post
}