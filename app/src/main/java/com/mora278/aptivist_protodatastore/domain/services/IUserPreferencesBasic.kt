package com.mora278.aptivist_protodatastore.domain.services

import com.mora278.aptivist_protodatastore.UserPreferencesBasic
import kotlinx.coroutines.flow.Flow

interface IUserPreferencesBasic {
    val data: Flow<UserPreferencesBasic>
    suspend fun setUserName(newUserName: String)
    suspend fun setAge(newAge: Int)
    suspend fun addTask(newTask: String)
}