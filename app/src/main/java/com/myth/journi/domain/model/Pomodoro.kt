package com.myth.journi.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = POMODOROS_TABLE,
    foreignKeys = [
        ForeignKey(
            entity = Actions::class,
            parentColumns = ["id"],
            childColumns = ["actionId"],
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class Pomodoro(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val actionId: Long,
    val runs: Int,
    val duration: Long,
    val shortRestDuration: Long,
    val longRestDuration: Long,
    val setsBeforeLongRest: Int,
)