package com.myth.journi.presentation.screens.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myth.journi.domain.model.Goal
import com.myth.journi.domain.usecase.GoalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalsViewModel @Inject constructor(
    private val goalUseCase: GoalUseCase
) : ViewModel() {
    fun saveGoal(
        goal: Goal,
        category: String = "",
        startDate: Long,
        endDate: Long,
        startTimeHour: Int,
        startTimeMinute: Int,
        endTimeHour: Int,
        endTimeMinute: Int,
        tasks: List<String>,
        onSuccess: (Boolean, String?) -> Unit
    ) = viewModelScope.launch {
        goalUseCase.saveGoal(
            goal,
            category,
            startDate,
            endDate,
            startTimeHour,
            startTimeMinute,
            endTimeHour,
            endTimeMinute,
            tasks,
            onSuccess
        )
    }
}