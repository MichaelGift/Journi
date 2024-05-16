package com.myth.journi.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diary")
data class DiaryModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val description: String,
    val date: Long,
    val mood: String
)