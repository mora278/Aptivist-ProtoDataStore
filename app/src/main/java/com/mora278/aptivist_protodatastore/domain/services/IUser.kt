package com.mora278.aptivist_protodatastore.domain.services

import com.mora278.aptivist_protodatastore.domain.models.User
import kotlinx.coroutines.flow.Flow

interface IUser {
    val users: Flow<List<User>>
    suspend fun updateUser(oldUSer: User, newUser: User)
    suspend fun addUser(newUser: User)
    suspend fun removeUser(user: User)
}