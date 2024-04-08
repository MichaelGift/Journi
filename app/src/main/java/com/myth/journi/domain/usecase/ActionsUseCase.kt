package com.myth.journi.domain.usecase

import android.util.Log
import com.myth.journi.domain.model.Action
import com.myth.journi.domain.repository.ActionRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class ActionsUseCase @Inject constructor(
    private val actionRepo: ActionRepo
) {
    fun getActionById(id: Long): Flow<Action> {
        var action: Flow<Action> = emptyFlow()
        try {
            action = actionRepo.getActionById(id)
        } catch (e: Exception) {
            Log.d("ActionsUseCase", e.message!!)
        }
        return action
    }

    fun getAllActions(): Flow<List<Action>> {
        var actions: Flow<List<Action>> = emptyFlow()
        try {
            actions = actionRepo.getAllActions()
        } catch (e: Exception) {
            Log.e("AllActionFlow", "${e.message}")
        }
        return actions
    }

    suspend fun updateTaskCompletion(value: Action) {
        try {
            actionRepo.updateAction(value)
        } catch (e: Exception) {
            Log.e("UpdateTaskCompletion", "${e.message}")
        }
    }
}