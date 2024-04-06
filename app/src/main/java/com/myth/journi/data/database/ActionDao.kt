package com.myth.journi.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.myth.journi.domain.model.ACTIONS_TABLE
import com.myth.journi.domain.model.Actions

@Dao
interface ActionDao {
    @Insert
    suspend fun addAction(action: Actions)

    @Update
    suspend fun updateAction(action: Actions)

    @Delete
    suspend fun deleteAction(actions: Actions)

    @Query("SELECT * FROM $ACTIONS_TABLE")
    suspend fun getAllActions(): List<Actions>

    @Query("SELECT * FROM $ACTIONS_TABLE WHERE startDate >= :today AND endDate <= :today")
    suspend fun getTodaysActions(today: Long): List<Actions>
}