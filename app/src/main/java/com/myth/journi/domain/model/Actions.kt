package com.myth.journi.domain.model

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
data class Actions(
    @PrimaryKey(autoGenerate = true)
    val actionId: Long,
    val pomodoroSettings: Pomodoro,
    val goalId: Long,
    val total: Int,
    val completed: Int,
    val startDate: Long,
    val endDate: Long,
    val timeBlockStart: Long,
    val timeBlockEnd: Long,
    val tasks: List<Task>,
)
