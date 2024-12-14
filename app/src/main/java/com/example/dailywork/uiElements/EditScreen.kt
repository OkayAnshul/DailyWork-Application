package com.example.dailywork.uiElements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.dailywork.database.Task
import com.example.dailywork.viewmodel.dwViewModel

@Composable
fun EditScreen(
    taskId:Long=-1L,
  //  onSave: (Task) -> Unit,
    onCancel: () -> Unit,
    viewModel:dwViewModel,
    navHostController: NavHostController
) {
    var taskName by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    var isTaskLoaded by remember { mutableStateOf(false) }

    // Fetch the task only if taskId > 0
    if (taskId > 0) {
        val task by viewModel.getTask(taskId).collectAsState(initial = null)
        LaunchedEffect(task) {
            if (task != null && !isTaskLoaded) {
                taskName = task!!.title
                taskDescription = task!!.description
                isTaskLoaded = true
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
           // .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = taskName,
            onValueChange = { taskName = it },
            textStyle = TextStyle(fontSize =16.sp),
            label = { Text("Task Name") },
            colors = OutlinedTextFieldDefaults.colors(Color.White
                , unfocusedTextColor = Color.LightGray),
        )
        OutlinedTextField(
            value = taskDescription,
            onValueChange = { taskDescription = it },
            textStyle = TextStyle(fontSize =16.sp),
            label = { Text("Task Description") },
            colors = OutlinedTextFieldDefaults.colors(Color.White
                , unfocusedTextColor = Color.LightGray)
        )
        Spacer(modifier = Modifier.padding(top = 6.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

            Button(onClick = onCancel) {
                Text("Cancel")
            }

            Button(onClick = {
                if (taskName.isNotBlank()) {
                    if(taskDescription.isBlank())
                        taskDescription=""
                    val newTask=Task(id =if(taskId>0)taskId else 0,
                        title = taskName.trim(),
                        description = taskDescription.trim())
                    if(taskId>0)
                        viewModel.editTask(newTask)
                    else
                    viewModel.addTask(newTask)

                    taskName =""
                    taskDescription = ""
                    navHostController.popBackStack()
                }
            }) {
                Text("Save")
            }
        }
    }
}



