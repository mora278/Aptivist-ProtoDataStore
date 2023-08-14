package com.mora278.aptivist_protodatastore.data.preferencesbasic

import android.util.Log
import androidx.datastore.core.DataStore
import com.mora278.aptivist_protodatastore.UserPreferencesBasic
import com.mora278.aptivist_protodatastore.domain.services.IUserPreferencesBasic
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import java.io.IOException
import javax.inject.Inject

class UserPreferencesBasicImpl @Inject constructor(
    private val userPreferencesStore: DataStore<UserPreferencesBasic>
) : IUserPreferencesBasic {
    override val data: Flow<UserPreferencesBasic>
        get() = userPreferencesStore.data.catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                Log.e(
                    "UserPreferencesBasicImpl",
                    "Error reading preferences.",
                    exception
                )
                emit(UserPreferencesBasic.getDefaultInstance())
            } else {
                throw exception
            }
        }

    override suspend fun setUserName(newUserName: String) {
        userPreferencesStore.updateData { preferences ->
            preferences
                .toBuilder()
                .setUserName(newUserName)
                .build()
        }
    }

    override suspend fun setAge(newAge: Int) {
        userPreferencesStore.updateData { preferences ->
            preferences
                .toBuilder()
                .setAge(newAge)
                .build()
        }
    }

    override suspend fun addTask(newTask: String) {
        userPreferencesStore.updateData { preferences ->
            preferences
                .toBuilder()
                .addTasks(newTask)
                .build()
        }
    }
}