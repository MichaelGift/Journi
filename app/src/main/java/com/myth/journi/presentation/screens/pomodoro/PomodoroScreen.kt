package com.myth.journi.presentation.screens.pomodoro

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.myth.journi.presentation.screens.pomodoro.components.Pomodoro
import com.myth.journi.presentation.screens.pomodoro.components.PomodoroTopBar
import com.myth.journi.presentation.screens.pomodoro.components.TaskCard

@Composable
fun PomodoroScreen(
    pomodoroViewModel: PomodoroViewModel = hiltViewModel(),
    tasksViewModel: TasksViewModel = hiltViewModel(),
    navController: NavController
) {
    val pomodoro = pomodoroViewModel.action.value
    val taskList = tasksViewModel.taskList.value

    Scaffold(topBar = {
        PomodoroTopBar(title = " ", onNavigationClick = {
            tasksViewModel.saveCompletedTasks()
            navController.navigateUp()
        })
    }) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            if (pomodoro != null) Pomodoro(settings = pomodoro)
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn {
                itemsIndexed(items = taskList) { index, item ->
                    if (!item.done) {
                        TaskCard(task = item) {
                            tasksViewModel.updateTaskList(index, it)
                        }
                    }
                }
            }
        }
    }
}