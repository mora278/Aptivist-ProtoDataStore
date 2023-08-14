package com.mora278.aptivist_protodatastore.domain.services

import com.mora278.aptivist_protodatastore.UserPreferencesTesting
import kotlinx.coroutines.flow.Flow

interface IUserPreferencesTesting {
    val data: Flow<UserPreferencesTesting>
    suspend fun syncDataIfNecessary()
    suspend fun setName(name: String)
    suspend fun setLastName(lastName: String)
    suspend fun setEMail(eMail: String)
    suspend fun setAge(age: String)
    suspend fun setDescription(description: String)
}