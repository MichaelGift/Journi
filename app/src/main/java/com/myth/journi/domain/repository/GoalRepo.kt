package com.myth.journi.domain.repository

import com.myth.journi.domain.model.Goal

interface GoalRepo {
    suspend fun saveGoal(goal: Goal)
    suspend fun updateGoal(goal: Goal)
    suspend fun deleteGoal(goal: Goal)
    suspend fun deleteGoals(goals: List<Goal>)
    suspend fun getAllGaols(): List<Goal>

}