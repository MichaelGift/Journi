package com.myth.journi.presentation.screens.task

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myth.journi.domain.model.Action
import com.myth.journi.domain.usecase.ActionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ActionState(
    val actionList: List<Action> = emptyList()
)

@HiltViewModel
class ActionsViewModel @Inject constructor(
    private val actionsUseCase: ActionsUseCase
) : ViewModel() {
    var actionState by mutableStateOf(ActionState())

    init {
        onEvent(ActionEvent.GetActions)
    }

    fun onEvent(event: ActionEvent) {
        viewModelScope.launch {
            when (event) {
                is ActionEvent.GetActions -> getAllActions()
            }
        }
    }

    private suspend fun getAllActions() {
        actionsUseCase.getAllActions().collect { actions ->
            actionState = actionState.copy(actionList = actions)
        }
    }
}

sealed class ActionEvent {
    data object GetActions : ActionEvent()
}