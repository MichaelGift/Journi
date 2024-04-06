package com.myth.journi.domain.model.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.myth.journi.domain.model.Task

class TaskConverter {
    @TypeConverter
    fun fromTask(task: Task): String {
        return Gson().toJson(task)
    }

    @TypeConverter
    fun toTask(task: String): Task {
        return Gson().fromJson(task, Task::class.java)
    }
}