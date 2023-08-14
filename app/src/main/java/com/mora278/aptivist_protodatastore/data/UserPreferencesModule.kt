package com.mora278.aptivist_protodatastore.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.mora278.aptivist_protodatastore.UserPreferencesAdvanced
import com.mora278.aptivist_protodatastore.UserPreferencesBasic
import com.mora278.aptivist_protodatastore.UserPreferencesTesting
import com.mora278.aptivist_protodatastore.data.preferencesadvanced.TaskImpl
import com.mora278.aptivist_protodatastore.data.preferencesadvanced.UserImpl
import com.mora278.aptivist_protodatastore.data.preferencesadvanced.UserPreferencesAdvancedSerializer
import com.mora278.aptivist_protodatastore.data.preferencesbasic.UserPreferencesBasicImpl
import com.mora278.aptivist_protodatastore.data.preferencesbasic.UserPreferencesBasicSerializer
import com.mora278.aptivist_protodatastore.data.preferencestesting.UserPreferencesTestingImpl
import com.mora278.aptivist_protodatastore.data.preferencestesting.UserPreferencesTestingSerializer
import com.mora278.aptivist_protodatastore.domain.services.ITask
import com.mora278.aptivist_protodatastore.domain.services.IUser
import com.mora278.aptivist_protodatastore.domain.services.IUserPreferencesBasic
import com.mora278.aptivist_protodatastore.domain.services.IUserPreferencesTesting
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private val Context.userPreferencesBasicStore: DataStore<UserPreferencesBasic> by dataStore(
    fileName = "user_prefs_basic.pb",
    serializer = UserPreferencesBasicSerializer
)

private val Context.userPreferencesAdvancedStore: DataStore<UserPreferencesAdvanced> by dataStore(
    fileName = "user_prefs_advanced.pb",
    serializer = UserPreferencesAdvancedSerializer
)

private val Context.userPreferencesTestingStore: DataStore<UserPreferencesTesting> by dataStore(
    fileName = "user_prefs_testing.pb",
    serializer = UserPreferencesTestingSerializer
)

@Module
@InstallIn(SingletonComponent::class)
object UserPreferencesModule {
    @Singleton
    @Provides
    fun provideUserPreferencesBasic(
        @ApplicationContext context: Context
    ): IUserPreferencesBasic = UserPreferencesBasicImpl(
        userPreferencesStore = context.userPreferencesBasicStore
    )

    @Singleton
    @Provides
    fun provideUserService(
        @ApplicationContext context: Context
    ): IUser = UserImpl(
        userPreferencesStore = context.userPreferencesAdvancedStore
    )

    @Singleton
    @Provides
    fun provideTaskService(
        @ApplicationContext context: Context
    ): ITask = TaskImpl(
        userPreferencesStore = context.userPreferencesAdvancedStore
    )

    @Singleton
    @Provides
    fun provideUserPreferencesTesting(
        @ApplicationContext context: Context
    ): IUserPreferencesTesting = UserPreferencesTestingImpl(
        userPreferencesStore = context.userPreferencesTestingStore
    )
}