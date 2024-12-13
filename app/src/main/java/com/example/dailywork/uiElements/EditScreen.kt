package com.example.dailywork.uiElements



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.dailywork.database.Task
import com.example.dailywork.viewmodel.dwViewModel

@Composable
fun EditScreen(
    task: Task? = null,
  //  onSave: (Task) -> Unit,
    onCancel: () -> Unit,
    viewModel:dwViewModel,
    navHostController: NavHostController
) {
    var taskName by remember { mutableStateOf(task?.title ?: "") }
    var taskDescription by remember { mutableStateOf(task?.description ?: "") }

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
            colors = OutlinedTextFieldDefaults.colors(Color.White)
        )
        OutlinedTextField(
            value = taskDescription,
            onValueChange = { taskDescription = it },
            textStyle = TextStyle(fontSize =16.sp),
            label = { Text("Task Description") },
            colors = OutlinedTextFieldDefaults.colors(Color.White)
        )
        Spacer(modifier = Modifier.padding(top = 6.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

            Button(onClick = onCancel) {
                Text("Cancel")
            }

            Button(onClick = {
                if (taskName.isNotBlank() && taskDescription.isNotBlank()) {
                    val newTask=Task(title = taskName.trim(), description = taskDescription.trim())
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



