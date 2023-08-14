package com.mora278.aptivist_protodatastore.domain.services

import androidx.datastore.preferences.core.Preferences
import com.mora278.aptivist_protodatastore.data.preferencesdatastore.PreferencesDataStoreModel
import kotlinx.coroutines.flow.Flow

interface IPreferencesDataStore {
    val data: Flow<PreferencesDataStoreModel>
    suspend fun update(key: Preferences.Key<String>, value: String)
    suspend fun update(key: Preferences.Key<Boolean>, value: Boolean)
    suspend fun update(key: Preferences.Key<Int>, value: Int)
}