package com.myth.journi.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.myth.journi.domain.model.Actions
import com.myth.journi.domain.model.Goal
import com.myth.journi.domain.model.Pomodoro
import com.myth.journi.domain.model.Task
import com.myth.journi.domain.model.converters.ActionsConverter
import com.myth.journi.domain.model.converters.PomodoroConverter
import com.myth.journi.domain.model.converters.TaskConverter


@Database(
    entities = [
        Goal::class,
        Actions::class,
        Pomodoro::class,
        Task::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    ActionsConverter::class,
    PomodoroConverter::class,
    TaskConverter::class
)
abstract class JourniDB : RoomDatabase() {
    abstract val goalDao: GoalDao
    abstract val actionDao: ActionDao
    abstract val pomodoroDao: PomodoroDao
    abstract val taskDao: TaskDao
}