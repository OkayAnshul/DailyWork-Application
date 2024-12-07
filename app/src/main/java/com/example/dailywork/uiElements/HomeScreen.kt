package com.example.dailywork.uiElements

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailywork.database.Task
import com.example.dailywork.viewmodel.dwViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: dwViewModel // Inject ViewModel
) {
    // Observe tasks and render in a LazyColumn
    LazyColumn(
        modifier = modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        items(viewModel.allTask.value) { task: Task ->
            // Each task is represented with a swipeable TaskBar
            SwipeableTaskBar(
                task = task,
                onDelete = { viewModel.removeTask(task) },
                onEdit = { /* Add edit logic here */ }
            )
        }
    }
}

@Composable
fun SwipeableTaskBar(
    task: Task,
    onDelete: () -> Unit,
    onEdit: () -> Unit
) {
    // State for swipe gesture
    val isSwiped = remember { mutableStateOf(false) }

    // Add swipe detection
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    change.consume()
                    isSwiped.value = dragAmount < -50 // Detect left swipe
                }
            }
    ) {
        // Show "Delete" button when swiped
        if (isSwiped.value) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.error)
                    .padding(8.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                TextButton(onClick = { onDelete() }) {
                    Text(
                        text = "DELETE",
                        color = MaterialTheme.colorScheme.onError,
                        fontSize = 16.sp
                    )
                }
            }
        } else {
            TaskBar(task = task, onEdit = onEdit, onDelete = onDelete)
        }
    }
}

@Composable
fun TaskBar(
    task: Task,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    // State to track task completion
    val isChecked = remember { mutableStateOf(task.isDone) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                // Task name
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        textDecoration = if (isChecked.value) TextDecoration.LineThrough else null
                    )
                )
                // Task description and time
                Text(
                    text = "Details: ${task.description}",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
//                Text(
//                    text = "Time: ${task.time}",
//                    style = MaterialTheme.typography.bodySmall,
//                    fontSize = 12.sp,
//                    color = MaterialTheme.colorScheme.onSurfaceVariant
//                )
            }

            // Mark as done
            Checkbox(
                checked = isChecked.value,
                onCheckedChange = {
                    isChecked.value = it
                    task.isDone = it // Update the task's status
                }
            )

            // Edit and Delete buttons
            Row {
                IconButton(onClick = { onEdit() }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit"
                    )
                }
                IconButton(onClick = { onDelete() }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete"
                    )
                }
            }
        }
    }
}
