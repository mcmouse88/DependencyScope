package com.mcmouse88.koinscopeapp.features.post.data.remote

import com.mcmouse88.koinscopeapp.features.post.domain.models.Post

interface PostService {
    suspend fun getPostList(): List<Post>
    suspend fun getPostById(id: Long): Post
}

class PostServiceImpl : PostService {

    override suspend fun getPostList(): List<Post> {
        return listOf(
            Post(
                id = 1L,
                title = "Post #1",
                description = "Description #1",
            ),
            Post(
                id = 2L,
                title = "Post #2",
                description = "Description #2",
            )
        )
    }

    override suspend fun getPostById(id: Long): Post {
        return Post(
            id = 1L,
            title = "Post #1",
            description = "Description #1",
        )
    }
}