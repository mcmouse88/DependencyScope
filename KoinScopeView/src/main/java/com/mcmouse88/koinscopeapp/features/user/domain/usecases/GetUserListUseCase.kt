package com.mcmouse88.koinscopeapp.features.user.domain.usecases

import android.util.Log
import com.mcmouse88.koinscopeapp.features.user.domain.models.User
import com.mcmouse88.koinscopeapp.features.user.domain.repositories.UserRepository

class GetUserListUseCase(
    private val repository: UserRepository
) {

    init {
        Log.e("TAG_CHECK", "$repository")
    }

    suspend operator fun invoke(): List<User> {
        return repository.getUsers()
    }
}