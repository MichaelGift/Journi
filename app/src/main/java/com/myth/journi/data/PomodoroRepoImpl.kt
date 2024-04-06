package com.myth.journi.data

import com.myth.journi.data.database.PomodoroDao
import com.myth.journi.domain.model.Pomodoro
import com.myth.journi.domain.repository.PomodoroRepo
import javax.inject.Inject

class PomodoroRepoImpl @Inject constructor(
    private val pomodoroDao: PomodoroDao
) : PomodoroRepo {
    override suspend fun addPomodoroSettings(pomodoro: Pomodoro) {
        pomodoroDao.addPomodoroSettings(pomodoro)
    }

    override suspend fun updatePomodoroSettings(pomodoro: Pomodoro) {
        pomodoroDao.updatePomodoroSettings(pomodoro)
    }

    override suspend fun deletePomodoroSettings(pomodoro: Pomodoro) {
        pomodoroDao.deletePomodoroSettings(pomodoro)
    }

    override suspend fun getAllPomodoroSettings(): List<Pomodoro> {
        return pomodoroDao.getAllPomodoroSettings()
    }

    override suspend fun getActionsPomodoro(id: Long): Pomodoro {
        return pomodoroDao.getActionsPomodoro(id)
    }
}