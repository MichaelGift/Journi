package com.myth.journi.presentation.screens.task

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myth.journi.domain.model.Goal
import com.myth.journi.domain.usecase.GoalUseCase
import com.myth.journi.presentation.events.GoalEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


data class GoalState(
    val goal: Goal? = null,

    val error: String? = null,
    val success: String? = null
)

@HiltViewModel
class GoalsViewModel @Inject constructor(
    private val goalUseCase: GoalUseCase
) : ViewModel() {

    var goalState by mutableStateOf(GoalState())

    fun onEvent(event: GoalEvent) {
        viewModelScope.launch {
            try {
                when (event) {
                    is GoalEvent.SaveGoal -> saveGoal(event)
                    is GoalEvent.ClearStateMessages -> {}
                }
            } catch (e: Exception) {
                goalState = goalState.copy(error = e.message)
                Log.e("GoalViewModel", e.toString(), e)
            }
        }
    }

    private suspend fun saveGoal(event: GoalEvent.SaveGoal) {
        goalUseCase.saveGoal(
            event.goal,
            event.category,
            event.startDate,
            event.endDate,
            event.startTimeHour,
            event.startTimeMinute,
            event.endTimeHour,
            event.endTimeMinute,
            event.tasks,
        ) { b: Boolean, s: String? ->
            if (b) goalState = goalState.copy(success = s)
        }
    }
}