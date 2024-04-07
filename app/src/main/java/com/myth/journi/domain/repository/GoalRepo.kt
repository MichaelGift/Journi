package com.myth.journi.domain.repository

import com.myth.journi.domain.model.Goal
import kotlinx.coroutines.flow.Flow

interface GoalRepo {
    suspend fun saveGoal(goal: Goal)
    suspend fun updateGoal(goal: Goal)
    suspend fun deleteGoal(goal: Goal)
    suspend fun deleteGoals(goals: List<Goal>)
    fun getAllGoals(): Flow<List<Goal>>
}