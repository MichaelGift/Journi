package com.myth.journi.presentation.screens.task

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Timelapse
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.myth.journi.domain.model.Goal
import com.myth.journi.presentation.events.GoalEvent
import com.myth.journi.presentation.screens.task.components.Chip
import com.myth.journi.presentation.screens.task.components.DatePickerDialog
import com.myth.journi.presentation.screens.task.components.GoalTopAppBar
import com.myth.journi.presentation.screens.task.components.TaskEntry
import com.myth.journi.presentation.screens.task.components.TimePickerDialog
import com.myth.journi.ui.theme.JourniTheme
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateGoalScreen(
    navigateBack: () -> Unit, goalEvent: (GoalEvent) -> Unit
) {

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    var showStartTimePicker by remember { mutableStateOf(false) }
    val startTimePickerState = rememberTimePickerState(
        initialHour = LocalTime.now().hour, initialMinute = LocalTime.now().minute
    )

    var showEndTimePicker by remember { mutableStateOf(false) }
    val endTimePickerState = rememberTimePickerState(
        initialHour = LocalTime.now().hour, initialMinute = LocalTime.now().minute
    )

    var showStartDatePicker by remember { mutableStateOf(false) }
    val startDatePickerState = rememberDatePickerState(
        initialSelectedDateMillis = Date().time
    )
    val startDateFormatted = remember(startDatePickerState.selectedDateMillis) {
        startDatePickerState.selectedDateMillis.let {
            SimpleDateFormat("EEE, d MMM yyyy").format(it)
        }
    }

    var showEndDatePicker by remember { mutableStateOf(false) }
    val endDatePickerState = rememberDatePickerState(
        initialSelectedDateMillis = Date().time
    )
    val endDateFormatted = remember(endDatePickerState.selectedDateMillis) {
        endDatePickerState.selectedDateMillis.let {
            SimpleDateFormat("EEE, d MMM yyyy").format(it)
        }
    }
    var tasks by remember { mutableStateOf(listOf("")) }


    Scaffold(
        topBar = {
            GoalTopAppBar(title = "Create Goal",
                onNavigationClick = { navigateBack() },
                onSaveIconClick = {
                    goalEvent(
                        GoalEvent.SaveGoal(
                            goal = Goal(
                                id = 0,
                                title = title,
                                description = description,
                            ),
                            category = "Personal",
                            startDate = startDatePickerState.selectedDateMillis!!,
                            endDate = endDatePickerState.selectedDateMillis!!,
                            startTimeHour = startTimePickerState.hour,
                            startTimeMinute = startTimePickerState.minute,
                            endTimeHour = endTimePickerState.hour,
                            endTimeMinute = endTimePickerState.minute,
                            tasks = tasks
                        )
                    )
                }
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState())
        ) {
            if (showStartTimePicker) {
                TimePickerDialog(onCancel = { showStartTimePicker = false },
                    onConfirm = { showStartTimePicker = false },
                    content = {
                        TimePicker(
                            state = startTimePickerState, modifier = Modifier.fillMaxWidth()
                        )
                    })
            }

            if (showEndTimePicker) {
                TimePickerDialog(onCancel = { showEndTimePicker = false },
                    onConfirm = { showEndTimePicker = false },
                    content = {
                        TimePicker(
                            state = endTimePickerState, modifier = Modifier.fillMaxWidth()
                        )
                    })
            }

            if (showStartDatePicker) {
                DatePickerDialog(onCancel = { showStartDatePicker = false },
                    onConfirm = { showStartDatePicker = false },
                    content = {
                        DatePicker(
                            state = startDatePickerState,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    })
            }

            if (showEndDatePicker) {
                DatePickerDialog(onCancel = { showEndDatePicker = false },
                    onConfirm = { showEndDatePicker = false },
                    content = {
                        DatePicker(
                            state = endDatePickerState,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                )
            }

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp),
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
                value = description,
                onValueChange = { description = it },
                label = { Text(text = "Description") })
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Start date")
                Chip(
                    clickEvent = { showStartDatePicker = true },
                    label = startDateFormatted,
                    icon = Icons.Filled.CalendarMonth
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(text = "End date")
                Chip(
                    clickEvent = { showEndDatePicker = true },
                    label = endDateFormatted,
                    icon = Icons.Filled.CalendarMonth
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Start time")
                Chip(
                    clickEvent = { showStartTimePicker = true }, label = String.format(
                        "%02d:%02d", startTimePickerState.hour, startTimePickerState.minute
                    ), icon = Icons.Filled.Timelapse
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "End time")
                Chip(
                    clickEvent = { showEndTimePicker = true }, label = String.format(
                        "%02d:%02d", endTimePickerState.hour, endTimePickerState.minute
                    ), icon = Icons.Filled.Timelapse
                )
            }

            tasks.forEachIndexed { index, task ->
                TaskEntry(task = task,
                    onValueChange = { updatedValue ->
                        tasks = tasks.toMutableList().also { it[index] = updatedValue }
                    },
                    onDelete = {
                        tasks = tasks.toMutableList().also { it.removeAt(index) }
                    },
                    onLeadingIconClick = {
                        tasks = if (index == tasks.size - 1) {
                            tasks.toMutableList().also { it.add("") }
                        } else tasks.toMutableList().also { it.removeAt(index) }
                        Log.d("CreateTask", tasks.toString())
                    },
                    leadingIcon = if (index == tasks.size - 1) Icons.Filled.Add else Icons.Filled.Close
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewCreateGoalScreen() {
    JourniTheme {
        CreateGoalScreen(navigateBack = {}, goalEvent = {})
    }
}