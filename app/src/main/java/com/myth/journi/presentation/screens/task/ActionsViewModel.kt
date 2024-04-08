package com.myth.journi.presentation.screens.task

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myth.journi.domain.model.Action
import com.myth.journi.domain.usecase.ActionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActionsViewModel @Inject constructor(
    private val actionsUseCase: ActionsUseCase
) : ViewModel() {
    private val _actions = mutableStateOf(listOf<Action>())
    val actions: State<List<Action>> = _actions

    init {
        viewModelScope.launch {
            getAllActions()
        }
    }

    private suspend fun getAllActions() {
        actionsUseCase.getAllActions().collect { actions ->
            Log.d("ActionsFlow", actions.toString())
            _actions.value = actions
        }
    }
}