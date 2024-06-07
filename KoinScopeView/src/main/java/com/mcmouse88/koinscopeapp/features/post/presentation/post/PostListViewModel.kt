package com.mcmouse88.koinscopeapp.features.post.presentation.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mcmouse88.koinscopeapp.features.post.domain.models.Post
import com.mcmouse88.koinscopeapp.features.post.domain.usecases.GetPostListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostListViewModel(
    getPostListUseCase: GetPostListUseCase
) : ViewModel() {

    private val _postListFlow = MutableStateFlow<List<Post>>(emptyList())
    val postListFlow = _postListFlow.asStateFlow()

    init {
        viewModelScope.launch {
            _postListFlow.tryEmit(getPostListUseCase.invoke())
        }
    }
}