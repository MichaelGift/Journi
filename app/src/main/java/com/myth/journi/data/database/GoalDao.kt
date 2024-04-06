package com.myth.journi.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.myth.journi.domain.model.GOALS_TABLE
import com.myth.journi.domain.model.Goal

@Dao
interface GoalDao {
    @Insert
    suspend fun saveGoals(goal: Goal)

    @Update
    suspend fun updateGoal(goal: Goal)

    @Delete
    suspend fun deleteGoal(goal: Goal)

    @Delete
    suspend fun deleteGoals(goals: List<Goal>)

    @Query("SELECT * From $GOALS_TABLE")
    suspend fun getAllGoals(): List<Goal>
}