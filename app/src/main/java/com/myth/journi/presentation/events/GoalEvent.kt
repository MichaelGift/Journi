package com.myth.journi.presentation.events

import com.myth.journi.domain.model.Goal

sealed class GoalEvent {

    data class SaveGoal(
        val goal:Goal,
        val category: String,
        val startDate: Long,
        val endDate: Long,
        val startTimeHour: Int,
        val startTimeMinute: Int,
        val endTimeHour: Int,
        val endTimeMinute: Int,
        val tasks: List<String>
    ): GoalEvent()
    data object ClearStateMessages: GoalEvent()
}