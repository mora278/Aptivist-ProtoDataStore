package com.mora278.aptivist_protodatastore.data.preferencesdatastore

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.mora278.aptivist_protodatastore.domain.services.IPreferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val USER_PREFERENCES_NAME = "preferences_data_store"

private val Context.dataStore by preferencesDataStore(
    name = USER_PREFERENCES_NAME
)

@Module
@InstallIn(SingletonComponent::class)
object PreferencesDataStoreModule {
     @Singleton
     @Provides
     fun providePreferencesDataStore(
         @ApplicationContext context: Context
     ): IPreferencesDataStore =
         PreferencesDataStoreImpl(
             dataStore = context.dataStore
         )
}