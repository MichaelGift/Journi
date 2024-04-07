package com.myth.journi.domain.model.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.myth.journi.domain.model.Pomodoro

class PomodoroConverter {
    @TypeConverter
    fun fromPomodoro(pomodoro: Pomodoro): String {
        return Gson().toJson(pomodoro)
    }

    @TypeConverter
    fun toPomodoro(pomodoro: String): Pomodoro {
        return Gson().fromJson(pomodoro, Pomodoro::class.java)
    }
}