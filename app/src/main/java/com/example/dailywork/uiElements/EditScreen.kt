package com.example.dailywork.uiElements



import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.dailywork.database.Task

@Composable
fun EditScreen(
    modifier: Modifier = Modifier,
    task: Task?,
    onSave: (Task) -> Unit,
    onCancel: () -> Unit
) {
    // State for editable fields
    var taskName by remember { mutableStateOf(task?.let { TextFieldValue(it.title) }) }
    var taskDescription by remember { mutableStateOf(task?.let { TextFieldValue(it.description) }) }
  //  var taskTime by remember { mutableStateOf(TextFieldValue(task.time)) }

    // Screen Layout
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        // Title
        Text(
            text = "Edit Task",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Task Name Input
        taskName?.let {
            OutlinedTextField(
                value = it,
                onValueChange = { taskName = it },
                label = { Text("Task Name") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )
        }

        // Task Description Input
        taskDescription?.let {
            OutlinedTextField(
                value = it,
                onValueChange = { taskDescription = it },
                label = { Text("Task Description") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )
        }

        // Task Time Input
//        OutlinedTextField(
//            value = taskTime,
//            onValueChange = { taskTime = it },
//            label = { Text("Task Time") },
//            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
//        )

        Spacer(modifier = Modifier.height(16.dp))

        // Action Buttons
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.align(Alignment.End)
        ) {
            // Cancel Button
            OutlinedButton(onClick = { onCancel() }) {
                Text("Cancel")
            }

            // Save Button
            Button(onClick = {
                if (task != null) {
                    taskName?.let {
                        taskDescription?.let { it1 ->
                            task.copy(
                                title = it.text,
                                description = it1.text,
                                //  time = taskTime.text
                            )
                        }
                    }?.let { onSave(it) }
                }
            }) {
                Text("Save")
            }
        }
    }
}
