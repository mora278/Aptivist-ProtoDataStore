package com.mora278.aptivist_protodatastore.data.preferencesadvanced

import android.util.Log
import androidx.datastore.core.DataStore
import com.mora278.aptivist_protodatastore.UserPreferencesAdvanced
import com.mora278.aptivist_protodatastore.data.Mapper.toDomain
import com.mora278.aptivist_protodatastore.data.Mapper.toProtoModel
import com.mora278.aptivist_protodatastore.domain.models.Task
import com.mora278.aptivist_protodatastore.domain.services.ITask
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class TaskImpl @Inject constructor(
    private val userPreferencesStore: DataStore<UserPreferencesAdvanced>
) : ITask {
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

    override val tasks: Flow<List<Task>> = flow {
        data.collect { newData ->
            val newTasks = newData.tasksList.map { it.toDomain() }
            emit(newTasks)
        }
    }

    override suspend fun updateTask(oldTask: Task, newTask: Task) {
        userPreferencesStore.updateData { preferences ->
            val index = preferences.tasksList.indexOf(oldTask.toProtoModel())
            preferences
                .toBuilder()
                .setTasks(index, newTask.toProtoModel())
                .build()
        }
    }

    override suspend fun addTask(newTask: Task) {
        userPreferencesStore.updateData { preferences ->
            preferences
                .toBuilder()
                .addTasks(newTask.toProtoModel())
                .build()
        }
    }

    override suspend fun removeTask(task: Task) {
        userPreferencesStore.updateData { preferences ->
            val index = preferences.tasksList.indexOf(task.toProtoModel())
            preferences
                .toBuilder()
                .removeTasks(index)
                .build()
        }
    }
}