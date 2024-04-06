package com.myth.journi.common.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.myth.journi.data.database.JourniDB
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
    fun providesJourniDB(@ApplicationContext context: Context): RoomDatabase = Room.databaseBuilder(
        context, JourniDB::class.java,
        DATABASE_NAME
    ).build()
}