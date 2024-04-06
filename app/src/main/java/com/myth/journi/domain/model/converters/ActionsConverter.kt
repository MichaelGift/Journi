package com.myth.journi.domain.model.converters


import androidx.room.TypeConverter
import com.google.gson.Gson
import com.myth.journi.domain.model.Actions


class ActionsConverter {
    @TypeConverter
    fun fromActions(actions: Actions): String {
        return Gson().toJson(actions)
    }

    @TypeConverter
    fun toActions(actions: String): Actions {
        return Gson().fromJson(actions, Actions::class.java)
    }
}