package com.myth.journi.presentation.screens.task.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chip(
    clickEvent: () -> Unit, label: String, icon: ImageVector
) {
    AssistChip(onClick = clickEvent, label = { Text(text = label) }, leadingIcon = {
        Icon(
            icon, contentDescription = null, Modifier.size(AssistChipDefaults.IconSize)
        )
    })
}