package com.myth.journi.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.myth.journi.domain.model.POMODOROS_TABLE
import com.myth.journi.domain.model.Pomodoro
import kotlinx.coroutines.flow.Flow


@Dao
interface PomodoroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPomodoroSettings(pomodoro: Pomodoro)

    @Update
    suspend fun updatePomodoroSettings(pomodoro: Pomodoro)

    @Delete
    suspend fun deletePomodoroSettings(pomodoro: Pomodoro)

    @Query("SELECT * FROM $POMODOROS_TABLE")
    suspend fun getAllPomodoroSettings(): List<Pomodoro>

    @Query("SELECT * FROM $POMODOROS_TABLE WHERE actionId = :id")
    fun getActionsPomodoro(id:Long): Flow<Pomodoro>
}