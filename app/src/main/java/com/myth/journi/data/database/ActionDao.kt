package com.myth.journi.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.myth.journi.domain.model.ACTIONS_TABLE
import com.myth.journi.domain.model.Action

@Dao
interface ActionDao {
    @Insert
    suspend fun addAction(action: Action)

    @Update
    suspend fun updateAction(action: Action)

    @Delete
    suspend fun deleteAction(action: Action)

    @Query("SELECT * FROM $ACTIONS_TABLE")
    suspend fun getAllActions(): List<Action>

    @Query("SELECT * FROM $ACTIONS_TABLE WHERE startDate >= :today AND endDate <= :today")
    suspend fun getTodaysActions(today: Long): List<Action>
}