package com.myth.journi.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = TASKS_TABLE,
    foreignKeys = [
        ForeignKey(
            entity = Actions::class,
            parentColumns = ["id"],
            childColumns = ["actionId"],
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(index = true)
    val actionId: Long,
    val done: Boolean,
    val description: String
)
