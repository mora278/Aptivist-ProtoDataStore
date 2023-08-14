package com.mora278.aptivist_protodatastore.data.preferencesadvanced

import android.util.Log
import androidx.datastore.core.DataStore
import com.mora278.aptivist_protodatastore.UserPreferencesAdvanced
import com.mora278.aptivist_protodatastore.data.Mapper.toDomain
import com.mora278.aptivist_protodatastore.data.Mapper.toProtoModel
import com.mora278.aptivist_protodatastore.domain.models.User
import com.mora278.aptivist_protodatastore.domain.services.IUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class UserImpl @Inject constructor(
    private val userPreferencesStore: DataStore<UserPreferencesAdvanced>
) : IUser {
    private val data: Flow<UserPreferencesAdvanced>
        get() = userPreferencesStore.data.catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                Log.e(
                    "UserImpl",
                    "Error reading sort order preferences.",
                    exception
                )
                emit(UserPreferencesAdvanced.getDefaultInstance())
            } else {
                throw exception
            }
        }

    override val users: Flow<List<User>> = flow {
        data.collect { newData ->
            val newUsers = newData.usersList.map { it.toDomain() }
            emit(newUsers)
        }
    }

    override suspend fun updateUser(oldUSer: User, newUser: User) {
        userPreferencesStore.updateData { preferences ->
            val index = preferences.usersList.indexOf(oldUSer.toProtoModel())
            if (index >= 0) {
                return@updateData preferences
                    .toBuilder()
                    .setUsers(index, newUser.toProtoModel())
                    .build()
            }
            preferences
        }
    }

    override suspend fun addUser(newUser: User) {
        userPreferencesStore.updateData { preferences ->
            preferences
                .toBuilder()
                .addUsers(newUser.toProtoModel())
                .build()
        }
    }

    override suspend fun removeUser(user: User) {
        userPreferencesStore.updateData { preferences ->
            val index = preferences.usersList.indexOf(user.toProtoModel())
            if (index >= 0) {
                return@updateData preferences
                    .toBuilder()
                    .removeUsers(index)
                    .build()
            }
            preferences
        }
    }
}