package com.myth.journi.domain.usecase

import android.util.Log
import com.myth.journi.domain.model.Goal
import com.myth.journi.domain.repository.GoalRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class GoalUseCase @Inject constructor(
    private val goalRepo: GoalRepo
) {
    suspend fun saveGoal(goal: Goal, onSuccess: (Boolean, String?) -> Unit) {
        try {
            goalRepo.saveGoal(goal)
            onSuccess(true, "Goal saved")
        } catch (e: Exception) {
            onSuccess(false, e.message)
        }
    }

    suspend fun updateGoal(goal: Goal, onSuccess: (Boolean, String?) -> Unit) {
        try {
            goalRepo.updateGoal(goal)
            onSuccess(true, "Goal updated")
        } catch (e: Exception) {
            onSuccess(false, e.message)
        }
    }

    suspend fun deleteGoal(goal: Goal, onSuccess: (Boolean, String?) -> Unit) {
        try {
            goalRepo.deleteGoal(goal)
            onSuccess(true, "Goal deleted")
        } catch (e: Exception) {
            onSuccess(false, e.message)
        }
    }

    suspend fun deleteGoals(goals: List<Goal>, onSuccess: (Boolean, String?) -> Unit) {
        try {
            goalRepo.deleteGoals(goals)
            onSuccess(true, "Goals Deleted")
        } catch (e: Exception) {
            onSuccess(false, e.message)
        }
    }

    fun getAllGoals(): Flow<List<Goal>> {
        var goals: Flow<List<Goal>> = emptyFlow()
        try {
            goals = goalRepo.getAllGoals()
        } catch (e: Exception) {
            Log.d("Goals Use Case", e.message!!)
        }
        return goals
    }

}