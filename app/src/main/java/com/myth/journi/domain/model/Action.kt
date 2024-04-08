package com.myth.journi.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = ACTIONS_TABLE,
    foreignKeys = [
        ForeignKey(
            entity = Goal::class,
            parentColumns = ["id"],
            childColumns = ["goalId"],
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class Action(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(index=true)
    val goalId: Long,
    val title: String,
    val category: String,
    val total: Int,
    var completed: Int,
    val startDate: Long,
    val endDate: Long,
    val timeBlockStart: Long,
    val timeBlockEnd: Long,
)
