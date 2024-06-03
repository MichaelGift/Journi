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
    val pomodoro: Pomodoro? = null
)

@HiltViewModel
class PomodoroViewModel @Inject constructor(
    private val pomodoroUseCase: PomodoroUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var pomodoroState by mutableStateOf(PomodoroState())

    init {
        savedStateHandle.get<Long>("actionId").let {actionId ->
            if (actionId?.toInt() == -1) return@let
            Log.d("PomodoroLog","$actionId")
            viewModelScope.launch { getPomodoroById(actionId!!) }
        }
    }

    private suspend fun getPomodoroById(actionId: Long) {
        pomodoroUseCase.getPomodoroById(actionId).collect { pomodoro ->
            Log.e("PomodoroFlow", "$pomodoro")
            pomodoroState = pomodoroState.copy(pomodoro = pomodoro)
        }
    }
}