package com.myth.journi.data.database

import com.myth.journi.domain.model.DiaryModel
import com.myth.journi.domain.repository.DiaryRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DiaryRepoImpl @Inject constructor(private val dao: DiaryDao) : DiaryRepo {
    override suspend fun addDiaryEntry(diary: DiaryModel) {
        dao.addDiaryEntry(diary)
    }

    override suspend fun updateDiaryEntry(diary: DiaryModel) {
        dao.updateDiaryEntry(diary)
    }

    override suspend fun deleteDiaryEntry(diary: DiaryModel) {
        dao.deleteDiaryEntry(diary)
    }

    override fun getAllDiaryEntries(): Flow<List<DiaryModel>> {
        return dao.getAllDiaryEntries()
    }
}