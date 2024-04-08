package com.myth.journi.domain.usecase

import android.util.Log
import com.myth.journi.domain.model.Task
import com.myth.journi.domain.repository.TaskRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject


class TasksUseCase @Inject constructor(
    private val taskRepo: TaskRepo
) {
    fun getTasksById(actionId: Long): Flow<List<Task>> {
        var taskList: Flow<List<Task>> = emptyFlow()
        try {
            taskList = taskRepo.getAllActionTasks(actionId)
        } catch (e: Exception) {
            Log.e("GetTasksById", "${e.message}")
        }
        return taskList
    }

    suspend fun updateTaskList(completedTasks: List<Task>) {
        taskRepo.updateCompletedTasks(completedTasks)
    }
}