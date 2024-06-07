package com.mcmouse88.koinscopeapp.features.user.domain.repositories

import com.mcmouse88.koinscopeapp.features.user.domain.models.User

interface UserRepository {

    suspend fun getUsers(): List<User>
    suspend fun getUserById(id: Long): User
}