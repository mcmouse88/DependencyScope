package com.mcmouse88.koinscopeapp.features.post.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mcmouse88.koinscopeapp.features.post.domain.models.Post
import com.mcmouse88.koinscopeapp.features.post.domain.usecases.GetPostDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class PostDetailViewModel(
    getPostDetailUseCase: GetPostDetailUseCase,
    id: Long
) : ViewModel() {

    private val _postDetailFlow = MutableStateFlow<Post?>(null)
    val postDetailFlow = _postDetailFlow.filterNotNull()

    init {
        viewModelScope.launch {
            _postDetailFlow.tryEmit(getPostDetailUseCase.invoke(id))
        }
    }
}