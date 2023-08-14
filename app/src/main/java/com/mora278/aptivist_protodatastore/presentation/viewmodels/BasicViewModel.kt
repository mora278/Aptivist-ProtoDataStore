package com.mora278.aptivist_protodatastore.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mora278.aptivist_protodatastore.domain.services.IUserPreferencesBasic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasicViewModel @Inject constructor(
    private val iUserPreferencesBasic: IUserPreferencesBasic
) : ViewModel() {
    val data = iUserPreferencesBasic.data

    fun updateUserData(newUserName: String, newAge: Int) {
        viewModelScope.launch {
            if (newUserName.isNotBlank()) {
                iUserPreferencesBasic.setUserName(newUserName)
            }
            if (newAge > 0) {
                iUserPreferencesBasic.setAge(newAge)
            }
        }
    }

    fun addTask(newTask: String) {
        viewModelScope.launch {
            iUserPreferencesBasic.addTask(newTask)
        }
    }
}