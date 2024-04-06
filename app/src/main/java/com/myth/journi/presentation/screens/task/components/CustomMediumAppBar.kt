package com.myth.journi.presentation.screens.task.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kizitonwose.calendar.core.Week
import com.myth.journi.presentation.screens.getWeekPageTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomMediumAppBar(
    visibleWeek: Week,
    onNavigationClick: () -> Unit,
    streak: String
) {
    MediumTopAppBar(
        title = { Text(text = getWeekPageTitle(visibleWeek)) },
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            Button(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.LocalFireDepartment,
                    contentDescription = "Streak"
                )
                Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))
                Text(text = streak, color = Color.White)
            }
        }
    )
}