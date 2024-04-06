package com.myth.journi.domain.repository

import com.myth.journi.domain.model.Pomodoro

interface PomodoroRepo {
    suspend fun addPomodoroSettings(pomodoro: Pomodoro)
    suspend fun updatePomodoroSettings(pomodoro: Pomodoro)
    suspend fun deletePomodoroSettings(pomodoro: Pomodoro)
    suspend fun getAllPomodoroSettings(): List<Pomodoro>
    suspend fun getActionsPomodoro(id: Long): Pomodoro
}