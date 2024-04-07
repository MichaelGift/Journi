package com.myth.journi.domain.repository

import com.myth.journi.domain.model.Task

interface TaskRepo {
    suspend fun addTask(task: Task)
    suspend fun addAllTasks(tasks: List<Task>)
    suspend fun updateTask(task: Task)
    suspend fun updateAllTasks(task: List<Task>)
    suspend fun deleteTask(task: Task)
    suspend fun deleteAllTasks(task: List<Task>)
    suspend fun getAllTasks(): List<Task>
    suspend fun getAllActionTasks(id: Long): List<Task>
}