package com.myth.journi.data

import com.myth.journi.data.database.ActionDao
import com.myth.journi.domain.model.Action
import com.myth.journi.domain.repository.ActionRepo
import javax.inject.Inject

class ActionRepoImpl @Inject constructor(
    private val actionDao: ActionDao
) : ActionRepo {
    override suspend fun addAction(action: Action) {
        actionDao.addAction(action)
    }

    override suspend fun updateAction(action: Action) {
        actionDao.updateAction(action)
    }

    override suspend fun deleteActions(action: Action) {
        actionDao.deleteAction(action)
    }

    override suspend fun getAllActions(): List<Action> {
        return actionDao.getAllActions()
    }

    override suspend fun getTodaysActions(today: Long): List<Action> {
        return actionDao.getTodaysActions(today)
    }
}