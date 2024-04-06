package com.myth.journi.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.myth.journi.domain.model.TASKS_TABLE
import com.myth.journi.domain.model.Task

@Dao
interface TaskDao {
    @Insert
    suspend fun addTask(task: Task)

    @Insert
    suspend fun addAllTasks(task: List<Task>)

    @Update
    suspend fun updateTask(task: Task)

    @Update
    suspend fun updateAllTasks(task: List<Task>)

    @Delete
    suspend fun deleteTask(task: Task)

    @Delete
    suspend fun deleteAllTasks(task: List<Task>)

    @Query("SELECT * FROM $TASKS_TABLE")
    suspend fun getAllTasks(): List<Task>

    @Query("SELECT * FROM $TASKS_TABLE WHERE actionId = :id")
    suspend fun getAllActionTasks(id: Long): List<Task>
}