package com.myth.journi.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.myth.journi.domain.model.POMODOROS_TABLE
import com.myth.journi.domain.model.Pomodoro


@Dao
interface PomodoroDao {
    @Insert
    suspend fun addPomodoroSettings(pomodoro: Pomodoro)

    @Update
    suspend fun updatePomodoroSettings(pomodoro: Pomodoro)

    @Delete
    suspend fun deletePomodoroSettings(pomodoro: Pomodoro)

    @Query("SELECT * FROM $POMODOROS_TABLE")
    suspend fun getAllPomodoroSettings(): List<Pomodoro>

    @Query("SELECT * FROM $POMODOROS_TABLE WHERE actionId = :id")
    suspend fun getActionsPomodoro(id:Long): Pomodoro
}