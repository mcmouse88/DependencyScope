package com.mcmouse88.koinscopeapp.features.post.data.repository

import com.mcmouse88.koinscopeapp.features.post.data.remote.PostService
import com.mcmouse88.koinscopeapp.features.post.domain.models.Post
import com.mcmouse88.koinscopeapp.features.post.domain.repository.PostRepository

class PostRepositoryImpl(
    private val postService: PostService
) : PostRepository {

    override suspend fun getPostList(): List<Post> {
        return postService.getPostList()
    }

    override suspend fun getPostById(id: Long): Post {
        return postService.getPostById(id)
    }
}