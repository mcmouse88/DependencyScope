package com.mcmouse88.koinscopeapp.features.user.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mcmouse88.koinscopeapp.features.user.domain.models.User
import com.mcmouse88.koinscopeapp.features.user.domain.usecases.GetUserListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserListViewModel(
    private val getUserListUseCase: GetUserListUseCase
) : ViewModel() {

    private val _userListFlow = MutableStateFlow<List<User>>(emptyList())
    val userListFlow = _userListFlow.asStateFlow()

    init {
        viewModelScope.launch {
            _userListFlow.update {
                getUserListUseCase.invoke()
            }
        }
    }
}