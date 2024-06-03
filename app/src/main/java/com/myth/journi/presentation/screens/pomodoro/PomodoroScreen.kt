package com.myth.journi.presentation.screens.pomodoro

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.myth.journi.presentation.screens.pomodoro.components.PomodoroCounter
import com.myth.journi.presentation.screens.pomodoro.components.PomodoroTopBar
import com.myth.journi.presentation.screens.pomodoro.components.TaskCard
import com.myth.journi.ui.theme.JourniTheme
import kotlin.time.Duration.Companion.minutes
import kotlin.time.DurationUnit

@Composable
fun PomodoroScreen(
    pomodoroState: PomodoroState,
    taskState: TaskState,
    taskEvent: (TaskEvent) -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = { PomodoroTopBar(title = " ", onNavigationClick = {
            taskEvent(TaskEvent.SaveCompletedTasks)
            onBack()
        })
    }) { paddingValues -> Column(
            modifier = Modifier.padding(paddingValues).fillMaxSize()
        ) {
            if (pomodoroState.pomodoro != null) PomodoroCounter(settings = pomodoroState.pomodoro)
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn {
                itemsIndexed(items = taskState.taskList) { index, item ->
                    if (!item.done) {
                        TaskCard(task = item) {
                            taskEvent(TaskEvent.UpdateTaskList(index, it))
                        }
                    }
                }
            }
        }
    }
}

val pomodoro = PomodoroState(
    pomodoro = com.myth.journi.domain.model.Pomodoro(
        id = 0,
        actionId = 0,
        runs = 2,
        duration = 1.minutes.toLong(DurationUnit.MILLISECONDS),
        shortRestDuration = 5.minutes.toLong(DurationUnit.MILLISECONDS),
        longRestDuration = 15.minutes.toLong(DurationUnit.MILLISECONDS),
        setsBeforeLongRest = 4
    )
)
val tasks =  TaskState(
    taskList = listOf(
        com.myth.journi.domain.model.Task(
            id = 0,
            actionId = 0,
            done = false,
            description = "Task 1"
        ),
        com.myth.journi.domain.model.Task(
            id = 1,
            actionId = 0,
            done = false,
            description = "Task 2"
        ),
        com.myth.journi.domain.model.Task(
            id = 2,
            actionId = 0,
            done = false,
            description = "Task 3"
        ),
    )
)
@Preview
@Composable
fun PomodoroScreenPreview() {
    JourniTheme(darkTheme = false) {
        PomodoroScreen(
            pomodoroState = pomodoro,
            taskState = tasks,
            taskEvent = {},
            onBack = {}
        )
    }
}

@Preview
@Composable
fun PomodoroScreenPreviewDark() {
    JourniTheme(darkTheme = true) {
        PomodoroScreen(
            pomodoroState = pomodoro,
            taskState = tasks,
            taskEvent = {},
            onBack = {}
        )
    }
}