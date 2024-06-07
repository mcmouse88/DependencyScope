package com.mcmouse88.koinscopeapp.features.user.data.repositories

import com.mcmouse88.koinscopeapp.features.user.data.remote.service.UserService
import com.mcmouse88.koinscopeapp.features.user.domain.models.User
import com.mcmouse88.koinscopeapp.features.user.domain.repositories.UserRepository

class UserRepositoryImpl(
    private val userService: UserService
) : UserRepository {

    override suspend fun getUsers(): List<User> {
        return userService.getUsers()
    }

    override suspend fun getUserById(id: Long): User {
        return userService.getUserById(id)
    }
}