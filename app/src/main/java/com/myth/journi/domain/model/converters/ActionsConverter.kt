package com.myth.journi.domain.model.converters


import androidx.room.TypeConverter
import com.google.gson.Gson
import com.myth.journi.domain.model.Action


class ActionsConverter {
    @TypeConverter
    fun fromActions(action: Action): String {
        return Gson().toJson(action)
    }

    @TypeConverter
    fun toActions(actions: String): Action {
        return Gson().fromJson(actions, Action::class.java)
    }
}