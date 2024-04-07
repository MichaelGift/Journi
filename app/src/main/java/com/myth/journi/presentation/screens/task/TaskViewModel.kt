package com.myth.journi.presentation.screens.task

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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
    private val _state = mutableStateOf(listOf<Goal>())
    val state: State<List<Goal>> = _state

    init {
        viewModelScope.launch {
            getAllGoals()
        }
    }
    private suspend fun getAllGoals() {

        goalUseCase.getAllGoals().collect { goals ->
            Log.d("GoalsLog", "Received goals: $goals")
            _state.value = goals
        }
    }

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
}