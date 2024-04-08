package com.myth.journi.domain.repository

import com.myth.journi.domain.model.Pomodoro
import kotlinx.coroutines.flow.Flow

interface PomodoroRepo {
    suspend fun addPomodoroSettings(pomodoro: Pomodoro)
    suspend fun updatePomodoroSettings(pomodoro: Pomodoro)
    suspend fun deletePomodoroSettings(pomodoro: Pomodoro)
    suspend fun getAllPomodoroSettings(): List<Pomodoro>
    fun getActionsPomodoro(id: Long): Flow<Pomodoro>
}