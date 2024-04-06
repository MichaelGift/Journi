package com.myth.journi.presentation.screens.task

import Day
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.kizitonwose.calendar.compose.WeekCalendar
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import com.myth.journi.presentation.screens.task.components.CustomMediumAppBar
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen() {
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
            CustomMediumAppBar(
                visibleWeek = visibleWeek,
                onNavigationClick = { /*TODO*/ },
                streak = "1"
            )
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
                modifier = Modifier.fillMaxSize()
            ) {
            }
        }
    }
}
