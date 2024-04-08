package com.myth.journi.presentation.screens.pomodoro

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myth.journi.domain.model.Action
import com.myth.journi.domain.model.Task
import com.myth.journi.domain.usecase.ActionsUseCase
import com.myth.journi.domain.usecase.TasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TasksViewModel @Inject constructor(
    private val tasksUseCase: TasksUseCase,
    private val actionsUseCase: ActionsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _taskList = mutableStateOf(listOf<Task>())
    val taskList: State<List<Task>> = _taskList
    private val _action = mutableStateOf<Action?>(null)


    init {
        savedStateHandle.get<Long>("actionId").let { actionId ->
            if (actionId?.toInt() == -1) return@let
            viewModelScope.launch { getTasksByActionId(actionId!!) }
            viewModelScope.launch { getActionById(actionId!!) }
        }
    }

    private suspend fun getActionById(actionId: Long) {
        actionsUseCase.getActionById(actionId).collect { action ->
            Log.d("TaskList", "Action is of type $action")
            _action.value = action
        }
    }

    private suspend fun getTasksByActionId(actionId: Long) {
        tasksUseCase.getTasksById(actionId).collect { tasks ->
            Log.d("TaskList", "$tasks")
            _taskList.value = tasks
        }
    }

    fun updateTaskList(index: Int, isChecked: Boolean) {
        val updatedTaskList = _taskList.value.toMutableList()
        updatedTaskList[index] = updatedTaskList[index].copy(done = isChecked)
        _taskList.value = updatedTaskList
        Log.d("TaskList", "${_taskList.value}")
    }

    fun saveCompletedTasks() = viewModelScope.launch {
        val completedTasks = _taskList.value.filter { it.done }
        tasksUseCase.updateTaskList(completedTasks)
        _action.value.let { it?.completed = completedTasks.size }
        actionsUseCase.updateTaskCompletion(_action.value!!)
    }
}