package com.mora278.aptivist_protodatastore.data.preferencestesting

import android.util.Log
import androidx.datastore.core.DataStore
import com.mora278.aptivist_protodatastore.UserPreferencesTesting
import com.mora278.aptivist_protodatastore.domain.services.IUserPreferencesTesting
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.lastOrNull
import java.io.IOException

class UserPreferencesTestingImpl(
    private val userPreferencesStore: DataStore<UserPreferencesTesting>
) : IUserPreferencesTesting {
    override val data: Flow<UserPreferencesTesting>
        get() = userPreferencesStore.data.catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                Log.e(
                    "UserPreferencesBasicImpl",
                    "Error reading sort order preferences.",
                    exception
                )
                emit(UserPreferencesTesting.getDefaultInstance())
            } else {
                throw exception
            }
        }

    override suspend fun syncDataIfNecessary() {
        userPreferencesStore.updateData { preferences ->
            preferences
                .toBuilder()
                .clear()
                .build()
        }
    }

    override suspend fun setName(name: String) {
        userPreferencesStore.updateData { preferences ->
            if (preferences.name != name) {
                preferences
                    .toBuilder()
                    .setName(name)
                    .build()
            } else preferences
        }
    }

    override suspend fun setLastName(lastName: String) {
        userPreferencesStore.updateData { preferences ->
            if (preferences.lastName != lastName) {
                preferences
                    .toBuilder()
                    .setLastName(lastName)
                    .build()
            } else preferences
        }
    }

    override suspend fun setEMail(eMail: String) {
        userPreferencesStore.updateData { preferences ->
            if (preferences.eMail != eMail) {
                preferences
                    .toBuilder()
                    .setEMail(eMail)
                    .build()
            } else preferences
        }
    }

    override suspend fun setAge(age: String) {
        userPreferencesStore.updateData { preferences ->
            if (preferences.age != age) {
                preferences
                    .toBuilder()
                    .setAge(age)
                    .build()
            } else preferences
        }
    }

    override suspend fun setDescription(description: String) {
        userPreferencesStore.updateData { preferences ->
            if (preferences.description != description) {
                preferences
                    .toBuilder()
                    .setDescription(description)
                    .build()
            } else preferences
        }
    }

}