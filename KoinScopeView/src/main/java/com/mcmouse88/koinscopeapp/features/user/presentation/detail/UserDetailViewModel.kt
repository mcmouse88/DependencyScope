package com.mcmouse88.koinscopeapp.features.user.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mcmouse88.koinscopeapp.features.user.domain.models.User
import com.mcmouse88.koinscopeapp.features.user.domain.usecases.GetUserDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserDetailViewModel(
    private val getUserDetailUseCase: GetUserDetailUseCase,
    private val id: Long
) : ViewModel() {

    private val _userFlow = MutableStateFlow<User?>(null)
    val userFlow = _userFlow.filterNotNull()

    init {
        viewModelScope.launch {
            _userFlow.update {
                getUserDetailUseCase.invoke(id)
            }
        }
    }
}