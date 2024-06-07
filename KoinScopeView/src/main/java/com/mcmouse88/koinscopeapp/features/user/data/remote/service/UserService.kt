package com.mcmouse88.koinscopeapp.features.user.data.remote.service

import com.mcmouse88.koinscopeapp.features.user.domain.models.User

interface UserService {
    suspend fun getUsers(): List<User>

    suspend fun getUserById(id: Long): User
}

class UserServiceImpl : UserService {
    override suspend fun getUsers(): List<User> {
        return listOf(
            User(id = 1, name = "David"),
            User(id = 2, name = "Peter")
        )
    }

    override suspend fun getUserById(id: Long): User {
        return User(id = 1, name = "David")
    }
}
