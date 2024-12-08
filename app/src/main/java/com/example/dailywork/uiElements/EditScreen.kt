package com.example.dailywork.uiElements



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dailywork.database.Task

@Composable
fun EditScreen(
    modifier: Modifier = Modifier,
    task: Task?,
    onSave: (Task) -> Unit,
    onCancel: () -> Unit
) {
    var taskName by remember { mutableStateOf(task?.title ?: "") }
    var taskDescription by remember { mutableStateOf(task?.description ?: "") }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = taskName,
            onValueChange = { taskName = it },
            label = { Text("Task Name") }
        )
        OutlinedTextField(
            value = taskDescription,
            onValueChange = { taskDescription = it },
            label = { Text("Task Description") }
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = onCancel) { Text("Cancel") }
            Button(onClick = {
                if (taskName.isNotBlank() && taskDescription.isNotBlank()) {
                    val updatedTask = task?.copy(
                        title = taskName,
                        description = taskDescription
                    ) ?: Task(title = taskName, description = taskDescription)
                    onSave(updatedTask)
                }
            }) {
                Text("Save")
            }
        }
    }
}



