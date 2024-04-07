package com.myth.journi.presentation.screens.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myth.journi.domain.model.Goal
import com.myth.journi.domain.usecase.GoalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val goalUseCase: GoalUseCase
) : ViewModel() {
    fun saveGoal(goal: Goal, onSuccess: (Boolean, String?) -> Unit) = viewModelScope.launch {
        goalUseCase.saveGoal(goal, onSuccess)
    }

    fun updateGoal(goal: Goal, onSuccess: (Boolean, String?) -> Unit) = viewModelScope.launch {
        goalUseCase.updateGoal(goal, onSuccess)
    }

    fun deleteGoal(goal: Goal, onSuccess: (Boolean, String?) -> Unit) = viewModelScope.launch {
        goalUseCase.deleteGoal(goal, onSuccess)
    }

    fun deleteGoals(goals: List<Goal>, onSuccess: (Boolean, String?) -> Unit) =
        viewModelScope.launch {
            goalUseCase.deleteGoals(goals, onSuccess)
        }

    fun getAllGoals(onSuccess: (Boolean, String?) -> Unit) = viewModelScope.launch {
        goalUseCase.getAllGoals(onSuccess)
    }
}