package com.mora278.aptivist_protodatastore.data.preferencesdatastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.mora278.aptivist_protodatastore.domain.services.IPreferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferencesDataStoreImpl(
    private val dataStore: DataStore<Preferences>,
) : IPreferencesDataStore {
    override val data: Flow<PreferencesDataStoreModel>
        get() = dataStore.data.map { preferences ->
            val newInt = preferences[PreferencesKeys.newInt]
            PreferencesDataStoreModel(
                name = preferences[PreferencesKeys.NAME] ?: "",
                lastName = preferences[PreferencesKeys.LAST_NAME] ?: "",
                eMail = preferences[PreferencesKeys.E_MAIL] ?: "",
                age = preferences[PreferencesKeys.AGE] ?: "",
                description = preferences[PreferencesKeys.DESCRIPTION] ?: "",
            )
        }

    override suspend fun update(key: Preferences.Key<String>, value: String) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    override suspend fun update(key: Preferences.Key<Boolean>, value: Boolean) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    override suspend fun update(key: Preferences.Key<Int>, value: Int) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }
}