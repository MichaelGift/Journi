package com.myth.journi.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.myth.journi.domain.model.TASKS_TABLE
import com.myth.journi.domain.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: Task)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun addAllTasks(task: List<Task>)

    @Update
    suspend fun updateTask(task: Task)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun updateCompletedTasks(task: List<Task>)

    @Delete
    suspend fun deleteTask(task: Task)

    @Delete
    suspend fun deleteAllTasks(task: List<Task>)

    @Query("SELECT * FROM $TASKS_TABLE")
    suspend fun getAllTasks(): List<Task>

    @Query("SELECT * FROM $TASKS_TABLE WHERE actionId = :id")
    fun getAllActionTasks(id: Long): Flow<List<Task>>
}