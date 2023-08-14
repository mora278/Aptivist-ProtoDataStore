package com.mora278.aptivist_protodatastore.data.sharedpreferences

import android.content.Context
import com.mora278.aptivist_protodatastore.domain.services.ISharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {
    @Singleton
    @Provides
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): ISharedPreferences {
        val sharedPreferences =
            context.getSharedPreferences("shared_preferences", Context.MODE_PRIVATE)
        return SharedPreferencesImpl(sharedPreferences = sharedPreferences)
    }
}