package com.myth.journi.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = TASKS_TABLE,
    foreignKeys = [
        ForeignKey(
            entity = Action::class,
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
    var done: Boolean,
    val description: String
)
