package com.myth.journi.domain.repository

import com.myth.journi.domain.model.Action
import kotlinx.coroutines.flow.Flow

interface ActionRepo {
    suspend fun addAction(action: Action): Long
    suspend fun updateAction(action: Action)
    suspend fun deleteActions(action: Action)
    fun getAllActions(): Flow<List<Action>>
    suspend fun getTodaysActions(today: Long): List<Action>

    fun getActionById(id: Long): Flow<Action>
}