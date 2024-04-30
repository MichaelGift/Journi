package com.myth.journi.data.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.myth.journi.domain.model.DiaryModel
import kotlinx.coroutines.flow.Flow

interface DiaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDiaryEntry(diaryModel: DiaryModel): Long

    @Update
    suspend fun updateDiaryEntry(diaryModel: DiaryModel)

    @Delete
    suspend fun deleteDiaryEntry(diaryModel: DiaryModel)

    @Query("SELECT * FROM diary")
    fun getAllDiaryEntries(): Flow<List<DiaryModel>>
}