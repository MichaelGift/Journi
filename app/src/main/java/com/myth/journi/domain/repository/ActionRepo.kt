package com.myth.journi.domain.repository

import com.myth.journi.domain.model.Action

interface ActionRepo {
    suspend fun addAction(action: Action)
    suspend fun updateAction(action: Action)
    suspend fun deleteActions(action: Action)
    suspend fun getAllActions(): List<Action>
    suspend fun getTodaysActions(today: Long): List<Action>
}