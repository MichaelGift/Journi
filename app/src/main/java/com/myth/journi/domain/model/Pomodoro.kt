package com.myth.journi.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = POMODOROS_TABLE,
    foreignKeys = [
        ForeignKey(
            entity = Action::class,
            parentColumns = ["id"],
            childColumns = ["actionId"],
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class Pomodoro(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(index = true)
    val actionId: Long,
    val runs: Int,
    val duration: Long,
    val shortRestDuration: Long,
    val longRestDuration: Long,
    val setsBeforeLongRest: Int,
)