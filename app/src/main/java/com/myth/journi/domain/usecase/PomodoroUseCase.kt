package com.myth.journi.domain.usecase

import android.util.Log
import com.myth.journi.domain.model.Pomodoro
import com.myth.journi.domain.repository.PomodoroRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class PomodoroUseCase @Inject constructor(
    private val pomodoroRepo: PomodoroRepo
) {
    fun getPomodoroById(actionId: Long): Flow<Pomodoro> {
        var pomodoro: Flow<Pomodoro> = emptyFlow()
        try {
            pomodoro = pomodoroRepo.getActionsPomodoro(actionId)
        } catch (e: Exception) {
            Log.e("PomodoroById", "${e.message}")
        }
        return pomodoro
    }

}