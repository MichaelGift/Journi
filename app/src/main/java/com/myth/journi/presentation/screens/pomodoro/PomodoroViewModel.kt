package com.myth.journi.presentation.screens.pomodoro

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myth.journi.domain.model.Pomodoro
import com.myth.journi.domain.usecase.PomodoroUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


data class PomodoroState(
    val pomodoro: Pomodoro? = null,
)

@HiltViewModel
class PomodoroViewModel @Inject constructor(
    private val pomodoroUseCase: PomodoroUseCase,
): ViewModel() {

    var pomodoroState by mutableStateOf(PomodoroState())


    fun onEvent(event: PomodoroEvent){
        viewModelScope.launch {
            when(event){
                is PomodoroEvent.GetPomodoro -> getPomodoroById(event)
            }
        }
    }
    private suspend fun getPomodoroById(event: PomodoroEvent.GetPomodoro) {
        pomodoroUseCase.getPomodoroById(event.actionId).collect { pomodoro ->
            pomodoroState = pomodoroState.copy(pomodoro = pomodoro)
        }
    }
}

sealed class PomodoroEvent {
    data class GetPomodoro(val actionId: Long): PomodoroEvent()
}