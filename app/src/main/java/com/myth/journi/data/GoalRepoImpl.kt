package com.myth.journi.data

import com.myth.journi.data.database.GoalDao
import com.myth.journi.domain.model.Goal
import com.myth.journi.domain.repository.GoalRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GoalRepoImpl @Inject constructor(
    private val goalDao: GoalDao
) : GoalRepo {
    override suspend fun saveGoal(goal: Goal) =
        goalDao.saveGoal(goal)

    override suspend fun updateGoal(goal: Goal) {
        goalDao.updateGoal(goal)
    }

    override suspend fun deleteGoal(goal: Goal) {
        goalDao.deleteGoal(goal)
    }

    override suspend fun deleteGoals(goals: List<Goal>) {
        goalDao.deleteGoals(goals)
    }

    override fun getAllGoals(): Flow<List<Goal>> {
        return goalDao.getAllGoals()
    }

}