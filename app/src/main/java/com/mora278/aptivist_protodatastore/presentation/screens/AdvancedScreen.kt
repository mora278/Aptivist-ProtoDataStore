package com.mora278.aptivist_protodatastore.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mora278.aptivist_protodatastore.domain.models.Task
import com.mora278.aptivist_protodatastore.domain.models.User
import com.mora278.aptivist_protodatastore.presentation.viewmodels.AdvancedViewModel

@Composable
fun AdvancedScreen() {
    val advancedViewModel: AdvancedViewModel = hiltViewModel()

    val users: List<User> by advancedViewModel.users.collectAsState(initial = emptyList())
    val tasks: List<Task> by advancedViewModel.tasks.collectAsState(initial = emptyList())

    var isAddUserDialogEnabled: Boolean by remember { mutableStateOf(false) }
    var isNewTaskDialogEnabled: Boolean by remember { mutableStateOf(false) }

    if (isAddUserDialogEnabled) {
        AddUserDialog(
            onDismissRequest = { isAddUserDialogEnabled = false },
            confirmButton = { advancedViewModel.addUser(it); isAddUserDialogEnabled = false }
        )
    }

    if (isNewTaskDialogEnabled) {
        NewTaskDialog(
            users = users,
            onDismissRequest = { isNewTaskDialogEnabled = false },
            confirmButton = { advancedViewModel.addTask(it); isNewTaskDialogEnabled = false }
        )
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Row {
                CustomButton(text = "Add User") {
                    isAddUserDialogEnabled = true
                }
                CustomButton(text = "Add Task") {
                    isNewTaskDialogEnabled = true
                }
            }
            SpacerVertical()
        }
        items(items = tasks) { task ->
            TaskCard(
                task = task,
                users = users,
                onCheckedChange = { advancedViewModel.updateTask(it, task) },
                onUserAssigned = { advancedViewModel.updateTask(it, task) },
                onClick = { taskSelected -> }
            )
            SpacerVertical()
        }
    }
}

@Composable
private fun SpacerVertical() {
    Spacer(modifier = Modifier.size(height = 8.dp, width = 0.dp))
}

@Composable
private fun CustomButton(
    text: String,
    onClick: () -> Unit
) {
    Button(onClick = onClick) {
        Text(text = text)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TaskCard(
    task: Task,
    users: List<User>,
    onCheckedChange: (Boolean) -> Unit,
    onUserAssigned: (User) -> Unit,
    onClick: (Task) -> Unit
) {
    var isDropDownMenuEnabled: Boolean by remember { mutableStateOf(false) }
    ElevatedCard(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        onClick = { onClick.invoke(task) }
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = task.isDone,
                onCheckedChange = { onCheckedChange.invoke(it) }
            )
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = task.description,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 21.sp)
                )
                if (users.isNotEmpty()) {
                    ShowDropdownMenuText(
                        users = users,
                        userAssigned = task.userAssigned,
                        isDropDownMenuEnabled = isDropDownMenuEnabled,
                        enableDropDownMenu = { isDropDownMenuEnabled = it },
                        onUserAssigned = { onUserAssigned.invoke(it); isDropDownMenuEnabled = false }
                    )
                }
            }
        }
    }
}

@Composable
private fun CustomText(
    text: String
) {
    Text(text = text)
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
private fun AddUserDialog(
    onDismissRequest: () -> Unit,
    confirmButton: (User) -> Unit
) {
    var userName: String by remember { mutableStateOf("") }
    var age: Int by remember { mutableStateOf(0) }
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            Button(
                onClick = { confirmButton.invoke(User(userName, age)) }
            ) {
                Text(text = "Add User")
            }
        },
        title = { Text(text = "New Task") },
        text = {
            Column {
                CustomTextField(label = "UserName", value = userName) { userName = it }
                SpacerVertical()
                CustomTextField(label = "Age", value = age.toString()) {
                    age = it.toIntOrNull() ?: 0
                }
            }
        }
    )
}

@Composable
private fun NewTaskDialog(
    users: List<User>,
    onDismissRequest: () -> Unit,
    confirmButton: (Task) -> Unit
) {
    var description: String by remember { mutableStateOf("") }
    var userAssigned: User? by remember { mutableStateOf(null) }
    var isDropDownMenuEnabled: Boolean by remember { mutableStateOf(false) }
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            Button(
                onClick = { confirmButton.invoke(Task(false, description, userAssigned)) }
            ) {
                Text(text = "Add Task")
            }
        },
        title = { Text(text = "New Task") },
        text = {
            Column {
                CustomTextField(label = "Description", value = description) { description = it }
                if (users.isNotEmpty()) {
                    SpacerVertical()
                    ShowDropdownMenuButton(
                        users = users,
                        userAssigned = userAssigned,
                        isDropDownMenuEnabled = isDropDownMenuEnabled,
                        enableDropDownMenu = { isDropDownMenuEnabled = it },
                        onUserAssigned = { userAssigned = it; isDropDownMenuEnabled = false }
                    )
                }
            }
        }
    )
}

@Composable
private fun ShowDropdownMenuButton(
    users: List<User>,
    userAssigned: User?,
    isDropDownMenuEnabled: Boolean,
    enableDropDownMenu: (Boolean) -> Unit,
    onUserAssigned: (User) -> Unit
) {
    Button(onClick = { enableDropDownMenu.invoke(true) }) {
        Text(text = userAssigned?.userName ?: "Select user")
        DropdownMenu(
            expanded = isDropDownMenuEnabled,
            onDismissRequest = { enableDropDownMenu.invoke(false) }) {
            users.forEach { user ->
                DropdownMenuItem(
                    text = {
                        Text(text = user.userName)
                    }, onClick = {
                        onUserAssigned.invoke(user)
                    }
                )
            }
        }
    }
}

@Composable
private fun ShowDropdownMenuText(
    users: List<User>,
    userAssigned: User?,
    isDropDownMenuEnabled: Boolean,
    enableDropDownMenu: (Boolean) -> Unit,
    onUserAssigned: (User) -> Unit
) {
    TextButton(onClick = { enableDropDownMenu.invoke(true) }) {
        Text(text = userAssigned?.userName ?: "Select user")
        DropdownMenu(
            expanded = isDropDownMenuEnabled,
            onDismissRequest = { enableDropDownMenu.invoke(false) }) {
            users.forEach { user ->
                DropdownMenuItem(
                    text = {
                        Text(text = user.userName)
                    }, onClick = {
                        onUserAssigned.invoke(user)
                    }
                )
            }
        }
    }
}