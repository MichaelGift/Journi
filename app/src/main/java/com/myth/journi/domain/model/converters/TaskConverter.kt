package com.myth.journi.domain.model.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import com.myth.journi.domain.model.Task

class TaskConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromTask(task: List<Task>): String {
        val jsonArray = JsonArray()
        task.forEach {
            val jsonObject = gson.toJsonTree(it).asJsonObject
            jsonArray.add(jsonObject)
        }
        return jsonArray.toString()
    }

    @TypeConverter
    fun toTask(task: String): List<Task> {
        val jsonArray = JsonParser.parseString(task).asJsonArray
        val taskList = mutableListOf<Task>()
        jsonArray.forEach { jsonObject ->
            taskList.add(gson.fromJson(jsonObject, Task::class.java))
        }
        return taskList
    }
}