package com.myth.journi.presentation.screens.task

import Day
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddTask
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kizitonwose.calendar.compose.WeekCalendar
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import com.myth.journi.common.utils.Screen
import com.myth.journi.presentation.screens.task.components.MonthTopAppBar
import com.myth.journi.presentation.screens.task.components.ActionStats
import java.time.LocalDate


@Composable
fun ActionsListScreen(
    navController: NavController,
    actionsViewModel: ActionsViewModel = hiltViewModel()
) {
    val actions = actionsViewModel.actions.value
    val currentDate = remember { LocalDate.now() }
    val startDate = remember { currentDate.minusDays(500) }
    val endDate = remember { currentDate.plusDays(500) }
    var selectedDate by remember { mutableStateOf(currentDate) }
    val state = rememberWeekCalendarState(
        startDate = startDate, endDate = endDate, firstVisibleWeekDate = currentDate
    )

    val visibleWeek = rememberFirstVisibleWeekAfterScroll(state = state)
    Scaffold(
        topBar = {
            MonthTopAppBar(
                visibleWeek = visibleWeek,
                onNavigationClick = { /*TODO*/ },
                streak = "1"
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.GoalCreationScreen.route)
            }) {
                Icon(
                    imageVector = Icons.Default.AddTask,
                    contentDescription = "Add Task"
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            WeekCalendar(
                state = state,
                dayContent = { day ->
                    Day(day.date, isSelected = selectedDate == day.date) { dateClicked ->
                        if (selectedDate != dateClicked) selectedDate = dateClicked
                    }
                }
            )
            LazyColumn(
                state = rememberLazyListState(),
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(items = actions) { actionItem ->
                    ActionStats(
                        item = actionItem,
                        modifier = Modifier
                            .clickable {
                                navController.navigate(
                                    Screen.PomodoroScreen.route + "?actionId=${actionItem.id}"
                                )
                            }
                            .padding(start = 12.dp, end = 12.dp, top = 4.dp, bottom = 4.dp)
                    )
                }
            }
        }
    }
}
