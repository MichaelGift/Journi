package com.myth.journi.common.di

import android.content.Context
import androidx.room.Room
import com.myth.journi.data.database.ActionDao
import com.myth.journi.data.database.GoalDao
import com.myth.journi.data.database.JourniDB
import com.myth.journi.data.database.PomodoroDao
import com.myth.journi.data.database.TaskDao
import com.myth.journi.domain.model.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Database {

    @Provides
    @Singleton
    fun providesJourniDB(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, JourniDB::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun providesGoalDao(journiDB: JourniDB): GoalDao {
        return journiDB.goalDao()
    }

    @Provides
    @Singleton
    fun provideActionDao(journiDB: JourniDB): ActionDao {
        return journiDB.actionDao()
    }

    @Provides
    @Singleton
    fun providesPomodoroDao(journiDB: JourniDB): PomodoroDao {
        return journiDB.pomodoroDao()
    }

    @Provides
    @Singleton
    fun providesTaskDao(journiDB: JourniDB): TaskDao {
        return journiDB.taskDao()
    }
}