package com.mora278.aptivist_protodatastore.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mora278.aptivist_protodatastore.UserPreferencesBasic
import com.mora278.aptivist_protodatastore.presentation.viewmodels.BasicViewModel

@Composable
fun BasicScreen(
    basicViewModel: BasicViewModel = hiltViewModel()
) {

//    val data: UserPreferencesBasic? by basicViewModel.data.collectAsState(initial = null)
    val data: UserPreferencesBasic by basicViewModel.data.collectAsState(initial = UserPreferencesBasic.getDefaultInstance())

    var newUserName: String by remember { mutableStateOf(data.userName ?: "") }
    var newAge: Int by remember { mutableStateOf(data.age) }
    var newTask: String by remember { mutableStateOf("") }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            CustomTextField(label = "New username", value = newUserName) { newUserName = it }
            SpacerVertical()
            CustomTextField(label = "New age", value = newAge.toString()) {
                newAge = it.toIntOrNull() ?: 0
            }
            SpacerVertical()
            UpdateButton(text = "Update user data") {
                basicViewModel.updateUserData(newUserName = newUserName, newAge = newAge)
            }
            SpacerVertical()
            CustomTextField(label = "New task", value = newTask) { newTask = it }
            SpacerVertical()
            UpdateButton(text = "Add task") {
                basicViewModel.addTask(newTask = newTask)
            }
            SpacerVertical()
            CustomText(text = "Username: ${data.userName}")
            SpacerVertical()
            CustomText(text = "age: ${data.age}")
            SpacerVertical()
            CustomText(text = "Tasks")
        }

        data.tasksList?.let { tasksList ->
            items(items = tasksList) { task ->
                SpacerVertical()
                CustomText(text = task)
            }
        }
    }
}

@Composable
private fun SpacerVertical() {
    Spacer(modifier = Modifier.size(height = 8.dp, width = 0.dp))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CustomTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        label = {
            Text(text = label)
        },
        value = value,
        onValueChange = onValueChange
    )
}

@Composable
private fun UpdateButton(
    text: String,
    onUpdateData: () -> Unit
) {
    Button(onClick = onUpdateData) {
        Text(text = text)
    }
}

@Composable
private fun CustomText(
    text: String
) {
    Text(text = text)
}