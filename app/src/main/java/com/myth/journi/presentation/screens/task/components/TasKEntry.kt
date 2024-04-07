package com.myth.journi.presentation.screens.task.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
@Composable
fun TaskEntry(
    task: String,
    onValueChange: (String) -> Unit,
    onDelete: () -> Unit,
    leadingIcon: ImageVector,
    onLeadingIconClick: () -> Unit = {}
) {
    TextField(
        value = task,
        onValueChange = onValueChange,
        leadingIcon = {
            IconButton(onClick = if (leadingIcon == Icons.Filled.Add) onLeadingIconClick else onDelete) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = if (leadingIcon == Icons.Filled.Add) "Add Task" else "Delete Task"
                )
            }
        },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "New checklist entry") }
    )
}