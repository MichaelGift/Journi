package com.myth.journi.data

import com.myth.journi.data.database.TaskDao
import com.myth.journi.domain.model.Task
import com.myth.journi.domain.repository.TaskRepo
import javax.inject.Inject

class TaskRepoImpl @Inject constructor(
    private val taskDao: TaskDao
) : TaskRepo {
    override suspend fun addTask(task: Task) {
        taskDao.addTask(task)
    }

    override suspend fun addAllTasks(tasks: List<Task>) {
        taskDao.addAllTasks(tasks)
    }

    override suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    override suspend fun updateAllTasks(task: List<Task>) {
        taskDao.updateAllTasks(task)
    }

    override suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }

    override suspend fun deleteAllTasks(task: List<Task>) {
        taskDao.deleteAllTasks(task)
    }

    override suspend fun getAllTasks(): List<Task> {
        return taskDao.getAllTasks()
    }

    override suspend fun getAllActionTasks(id: Long): List<Task> {
        return taskDao.getAllActionTasks(id)
    }
}