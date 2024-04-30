package com.myth.journi.domain.repository

import com.myth.journi.domain.model.DiaryModel
import kotlinx.coroutines.flow.Flow

interface DiaryRepo {

    suspend fun addDiaryEntry(diary: DiaryModel)
    suspend fun updateDiaryEntry(diary: DiaryModel)
    suspend fun deleteDiaryEntry(diary: DiaryModel)
    fun getAllDiaryEntries(): Flow<List<DiaryModel>>
}