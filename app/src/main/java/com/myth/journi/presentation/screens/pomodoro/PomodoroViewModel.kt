package com.myth.journi.presentation.screens.pomodoro

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myth.journi.domain.model.Pomodoro
import com.myth.journi.domain.usecase.PomodoroUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PomodoroViewModel @Inject constructor(
    private val pomodoroUseCase: PomodoroUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _action = mutableStateOf<Pomodoro?>(null)
    val action: State<Pomodoro?> = _action
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
            _action.value = pomodoro
        }
    }
}