package com.myth.journi.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.myth.journi.domain.model.GOALS_TABLE
import com.myth.journi.domain.model.Goal
import kotlinx.coroutines.flow.Flow

@Dao
interface GoalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveGoal(goal: Goal): Long

    @Update
    suspend fun updateGoal(goal: Goal)

    @Delete
    suspend fun deleteGoal(goal: Goal)

    @Delete
    suspend fun deleteGoals(goals: List<Goal>)

    @Query("SELECT * From $GOALS_TABLE")
    fun getAllGoals(): Flow<List<Goal>>
}