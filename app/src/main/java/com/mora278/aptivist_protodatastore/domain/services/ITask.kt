package com.mora278.aptivist_protodatastore.domain.services

import com.mora278.aptivist_protodatastore.domain.models.Task
import kotlinx.coroutines.flow.Flow

interface ITask {
    val tasks: Flow<List<Task>>
    suspend fun updateTask(oldTask: Task, newTask: Task)
    suspend fun addTask(newTask: Task)
    suspend fun removeTask(task: Task)
}