package com.myth.journi.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.myth.journi.domain.model.Action
import com.myth.journi.domain.model.DiaryModel
import com.myth.journi.domain.model.Goal
import com.myth.journi.domain.model.Pomodoro
import com.myth.journi.domain.model.Task
import com.myth.journi.domain.model.converters.ActionsConverter
import com.myth.journi.domain.model.converters.PomodoroConverter
import com.myth.journi.domain.model.converters.TaskConverter


@Database(
    entities = [
        Goal::class,
        Action::class,
        Pomodoro::class,
        Task::class,
        DiaryModel::class
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
    abstract fun goalDao(): GoalDao
    abstract fun actionDao(): ActionDao
    abstract fun pomodoroDao(): PomodoroDao
    abstract fun taskDao(): TaskDao

    abstract fun diaryDao(): DiaryDao
}