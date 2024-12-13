package com.example.dailywork.uiElements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailywork.R
import com.example.dailywork.database.Task
import com.example.dailywork.viewmodel.dwViewModel


object DummyData{
    val task= listOf(Task(0,"One","desc1"),
        Task(1,"Two","desc2"),
        Task(2,"Three","desc3"),
        Task(3,"Four","desc4"),
        Task(4,"Five","desc5"))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: dwViewModel
) {
    // Observe tasks using collectAsState
    val tasks = viewModel.allTask.collectAsState(initial = emptyList())

    if (tasks.value.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("No tasks available!", style = MaterialTheme.typography.titleMedium)
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = tasks.value,
                key = {task -> task.id}) { task ->
                val dismissBoxState = rememberSwipeToDismissBoxState(
                    confirmValueChange = { state ->

                        if (state == SwipeToDismissBoxValue.EndToStart) {
                            viewModel.removeTask(task)
                        }
                        true
                    }
                )
                SwipeToDismissBox(
                    state = dismissBoxState,
                    backgroundContent = {
                    },
                    enableDismissFromEndToStart = true,
                    enableDismissFromStartToEnd = false
                )
                {
                    TaskBar(
                        task = task,
                        onDelete = {
                            viewModel.removeTask(task)
                        },
                        onEdit = {
                        },
                        onTaskStatusChange = {
                            //isDone ->
                            //viewModel.editTask(task.copy(isDone = isDone))
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun TaskBar(
    task: Task,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onTaskStatusChange: (Boolean) -> Unit
) {
    val DarkGreen = Color(0xFF00334d)
        Card(
            modifier = Modifier
                .clickable { onEdit() }
                .fillMaxWidth()
                .padding(horizontal = 6.dp),
            colors = CardDefaults.cardColors(DarkGreen),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Task details column
                Column(
                    modifier = Modifier.weight(1f) // Take remaining space
                ) {
                    Text(
                        text = task.title,
                        fontFamily = FontFamily(Font(R.font.allerta_stencil)),
                        fontSize =16.sp,
                        style = MaterialTheme.typography.titleMedium.copy(
                            textDecoration = if (task.isDone) TextDecoration.LineThrough else null
                        ),
                        color = Color.White
                    )
                    if (task.description.isNotBlank()) {
                        Row() {
                            Spacer(Modifier.padding(2.dp))
                            Text(modifier = Modifier.padding(2.dp),
                                text = task.description,
                                fontFamily = FontFamily(Font(R.font.bellota_italic)),
                                fontStyle = FontStyle.Normal,
                                style = MaterialTheme.typography.bodySmall,
                                fontSize = 12.sp,
                                color = Color.LightGray
                            )
                        }
                    }
                }

                // Actions: Checkbox and Delete
                Row(horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = task.isDone,
                        onCheckedChange = { isDone -> onTaskStatusChange(isDone) },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color.LightGray,
                            uncheckedColor = Color.LightGray,
                            checkmarkColor = DarkGreen
                        )
                    )
                    // Spacer(modifier = Modifier.width(8.dp)) // Adds spacing
//                    IconButton(onClick = onDelete) {
//                        Icon(
//                            imageVector = Icons.Default.Delete,
//                            contentDescription = "Delete Task",
//                            tint = Color.White
//                        )
//                    }
                }
            }
        }
    }



@Composable
fun ConfirmDeleteDialog(
    task: Task,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Delete Task") },
        text = { Text("Are you sure you want to delete '${task.title}'?") },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("Delete")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
