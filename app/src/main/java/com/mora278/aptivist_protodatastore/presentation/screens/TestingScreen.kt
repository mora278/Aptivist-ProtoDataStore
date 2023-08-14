package com.mora278.aptivist_protodatastore.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
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
import com.mora278.aptivist_protodatastore.UserPreferencesTesting
import com.mora278.aptivist_protodatastore.presentation.viewmodels.TestingViewModel

@Composable
fun TestingScreen() {
    val testingViewModel: TestingViewModel = hiltViewModel()
    val data: UserPreferencesTesting by testingViewModel.data.collectAsState(initial = UserPreferencesTesting.getDefaultInstance())
    data.isInitialized

    var name: String by remember { mutableStateOf("") }
    var lastName: String by remember { mutableStateOf("") }
    var eMail: String by remember { mutableStateOf("") }
    var age: String by remember { mutableStateOf("") }
    var description: String by remember { mutableStateOf("") }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        addStringField(
            text = "Current name: ${data.name}",
            label = "Name",
            placeholder = data.name,
            value = name,
            onValueChange = { name = it }
        )
        addStringField(
            text = "Current last name: ${data.lastName}",
            label = "Last name",
            placeholder = data.lastName,
            value = lastName,
            onValueChange = { lastName = it }
        )
        addStringField(
            text = "Current e-mail: ${data.eMail}",
            label = "e-mail",
            placeholder = data.eMail,
            value = eMail,
            onValueChange = { eMail = it }
        )
        addStringField(text = "Current age: ${data.age}",
            label = "Age",
            placeholder = data.age,
            value = age,
            onValueChange = { age = it }
        )
        addStringField(text = "Current description: ${data.description}",
            label = "Description",
            placeholder = data.description,
            value = description,
            onValueChange = { description = it }
        )
        addUpdateButton(
            text = "Update values",
            onUpdateData = {
                testingViewModel.updateUserData(
                    name = name.ifBlank { data.name },
                    lastName = lastName.ifBlank { data.lastName },
                    eMail = eMail.ifBlank { data.eMail },
                    age = age.ifBlank { data.age },
                    description = description.ifBlank { data.description }
                )
            }
        )
        item { SpacerVertical() }
        addUpdateButton(
            text = "Delete values",
            onUpdateData = {
                testingViewModel.deleteData()
            }
        )
    }
}

private fun LazyListScope.addStringField(
    text: String,
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    item {
        CustomText(
            text = text
        )
        SpacerVertical()
        CustomTextField(
            label = label,
            placeholder = placeholder,
            value = value,
            onValueChange = onValueChange
        )
        SpacerVertical()
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
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        label = {
            Text(text = label)
        },
        placeholder = {
            Text(text = placeholder)
        },
        value = value,
        onValueChange = onValueChange
    )
}

@Composable
private fun CustomText(
    text: String
) {
    Text(text = text)
}

private fun LazyListScope.addUpdateButton(
    text: String, onUpdateData: () -> Unit
) {
    item {
        Button(onClick = onUpdateData) {
            Text(text = text)
        }
    }
}