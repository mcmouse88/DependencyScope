package com.mcmouse88.koinscopeapp.features.post.domain.usecases

import android.util.Log
import com.mcmouse88.koinscopeapp.features.post.domain.models.Post
import com.mcmouse88.koinscopeapp.features.post.domain.repository.PostRepository

class GetPostDetailUseCase(
    private val repository: PostRepository
) {
    init {
        Log.e("TAG_CHECK", "PostRepository $repository")
    }

    suspend operator fun invoke(id: Long): Post {
        return repository.getPostById(id)
    }
}