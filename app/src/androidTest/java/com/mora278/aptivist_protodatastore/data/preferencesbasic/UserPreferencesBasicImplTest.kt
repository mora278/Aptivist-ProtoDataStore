package com.mora278.aptivist_protodatastore.data.preferencesbasic

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.mora278.aptivist_protodatastore.UserPreferencesBasic
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserPreferencesBasicImplTest {
    companion object {
        private val Context.userPreferencesBasicStore: DataStore<UserPreferencesBasic> by dataStore(
            fileName = "user_prefs_basic.pb",
            serializer = UserPreferencesBasicSerializer
        )
    }
    private lateinit var userPreferencesBasicImpl: UserPreferencesBasicImpl

    @Before
    fun setUp() {
        runBlocking {
            val context = InstrumentationRegistry.getInstrumentation().targetContext
            val userPreferencesStore = context.userPreferencesBasicStore
            userPreferencesStore.updateData { preferences ->
                preferences
                    .toBuilder()
                    .mergeFrom(UserPreferencesBasic.getDefaultInstance())
                    .build()
            }
            userPreferencesBasicImpl =
                UserPreferencesBasicImpl(userPreferencesStore = userPreferencesStore)
        }
    }

    @Test
    fun test_setUserName() {
        runBlocking {
            val fakeUserName = "Mora278"
            userPreferencesBasicImpl.setUserName(newUserName = fakeUserName)
            val userName = userPreferencesBasicImpl.data.first().userName
            assertEquals(fakeUserName, userName)
        }
    }

    @Test
    fun test_setAge() {
        runBlocking {
            val fakeAge = 24
            userPreferencesBasicImpl.setAge(newAge = fakeAge)
            val age = userPreferencesBasicImpl.data.first().age
            assertEquals(fakeAge, age)
        }
    }

    @Test
    fun test_addSingleTask() {
        runBlocking {
            val fakeTask = "Test your implementation"
            userPreferencesBasicImpl.addTask(fakeTask)
            val tasks = userPreferencesBasicImpl.data.first().tasksList
            assert(tasks.contains(fakeTask))
        }
    }
}