package com.myth.journi.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = GOALS_TABLE)
data class Goal(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val category: String,
    val actionSteps: Actions,
)
