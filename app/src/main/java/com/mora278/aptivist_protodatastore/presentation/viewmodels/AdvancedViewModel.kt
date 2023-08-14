package com.mora278.aptivist_protodatastore.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mora278.aptivist_protodatastore.domain.models.Task
import com.mora278.aptivist_protodatastore.domain.models.User
import com.mora278.aptivist_protodatastore.domain.services.ITask
import com.mora278.aptivist_protodatastore.domain.services.IUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdvancedViewModel @Inject constructor(
    private val iUser: IUser,
    private val iTask: ITask
) : ViewModel() {
    val users = iUser.users
    val tasks = iTask.tasks

    fun addUser(user: User) {
        viewModelScope.launch {
            iUser.addUser(user)
        }
    }

    fun addTask(task: Task) {
        viewModelScope.launch {
            iTask.addTask(task)
        }
    }

    fun updateTask(isDone: Boolean, task: Task) {
        viewModelScope.launch {
            iTask.updateTask(task, task.copy(isDone = isDone))
        }
    }

    fun updateTask(userAssigned: User, task: Task) {
        viewModelScope.launch {
            iTask.updateTask(task, task.copy(userAssigned = userAssigned))
        }
    }
}