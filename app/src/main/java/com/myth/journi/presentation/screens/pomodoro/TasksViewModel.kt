package com.myth.journi.presentation.screens.pomodoro

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myth.journi.domain.model.Action
import com.myth.journi.domain.model.Task
import com.myth.journi.domain.usecase.ActionsUseCase
import com.myth.journi.domain.usecase.TasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.KType
import kotlin.reflect.full.starProjectedType
import kotlin.reflect.typeOf


data class TaskState(
    val currentAction: Action? = null,
    val taskList: List<Task> = emptyList()
)

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val tasksUseCase: TasksUseCase,
    private val actionsUseCase: ActionsUseCase,
) : ViewModel() {
    var taskState by mutableStateOf(TaskState())

    private val taskEventMap = mapOf(
        TaskEventPair().build<TaskEvent.SaveCompletedTasks> { saveCompletedTasks() },
        TaskEventPair().build<TaskEvent.UpdateTaskList> { updateTaskList(it.index, it.isChecked) },
        TaskEventPair().build<TaskEvent.GetTasks> { viewModelScope.launch { getActionById(it.actionId) } },
        TaskEventPair().build<TaskEvent.GetAction> { viewModelScope.launch { getTasksByActionId(it.actionId) } }
    )

    fun onEvent(event: TaskEvent) {
        val eventType = event::class.starProjectedType
        val handler = taskEventMap[eventType]
            ?: throw IllegalArgumentException("No handler for type: $eventType")
        handler(event)
    }

    private suspend fun getActionById(actionId: Long) {
        actionsUseCase.getActionById(actionId).collect { action ->
            taskState = taskState.copy(currentAction = action)
        }
    }

    private suspend fun getTasksByActionId(actionId: Long) {
        tasksUseCase.getTasksById(actionId).collect { tasks ->
            taskState = taskState.copy(taskList = tasks)
        }
    }

    private fun updateTaskList(index: Int, isChecked: Boolean) {
        taskState = taskState.copy(
            taskList = taskState.taskList.mapIndexed { i, task ->
                if (i == index)
                    task.copy(done = isChecked)
                else
                    task
            }
        )
    }

    private fun saveCompletedTasks() = viewModelScope.launch {
        val completedTasks = taskState.taskList.filter { it.done }
        tasksUseCase.updateTaskList(completedTasks)
    }
}

sealed class TaskEvent {
    data object SaveCompletedTasks : TaskEvent()
    data class UpdateTaskList(val index: Int, val isChecked: Boolean) : TaskEvent()
    data class GetTasks(val actionId: Long) : TaskEvent()
    data class GetAction(val actionId: Long) : TaskEvent()
}

class TaskEventPair {
    inline fun <reified T : TaskEvent> build(
        crossinline delegate: (T) -> Unit
    ): Pair<KType, (TaskEvent) -> Unit> {
        return Pair(typeOf<T>()) {
            if (it is T) delegate(it)
            else throw IllegalArgumentException("Invalid TaskEvent type: expected ${typeOf<T>()}, got ${it::class}")
        }
    }
}