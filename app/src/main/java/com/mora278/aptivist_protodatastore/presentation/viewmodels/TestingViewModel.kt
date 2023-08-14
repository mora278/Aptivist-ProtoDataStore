package com.mora278.aptivist_protodatastore.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mora278.aptivist_protodatastore.domain.services.IUserPreferencesTesting
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestingViewModel @Inject constructor(
    private val iUserPreferencesTesting: IUserPreferencesTesting
) : ViewModel() {
    val data = iUserPreferencesTesting.data

    fun updateUserData(
        name: String,
        lastName: String,
        eMail: String,
        age: String,
        description: String
    ) {
        viewModelScope.launch {
            iUserPreferencesTesting.setName(name)
            iUserPreferencesTesting.setLastName(lastName)
            iUserPreferencesTesting.setEMail(eMail)
            iUserPreferencesTesting.setAge(age)
            iUserPreferencesTesting.setDescription(description)
        }
    }

    fun deleteData() {
        viewModelScope.launch {
            iUserPreferencesTesting.syncDataIfNecessary()
        }
    }
}