package com.myth.journi.domain.usecase

import com.myth.journi.domain.model.DiaryModel
import com.myth.journi.domain.repository.DiaryRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DiaryUseCase @Inject constructor(
    private val repo: DiaryRepo
) {
    suspend fun addDiaryEntry(diary: DiaryModel) {
        repo.addDiaryEntry(diary)
    }

    suspend fun updateDiaryEntry(diary: DiaryModel) {
        repo.deleteDiaryEntry(diary)
    }

    suspend fun deleteDairyEntry(diary: DiaryModel) {
        repo.deleteDiaryEntry(diary)
    }

    fun getAllDiaryEntries(): Flow<List<DiaryModel>> {
        return repo.getAllDiaryEntries()
    }
}