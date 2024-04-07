package com.myth.journi.common.di

import com.myth.journi.data.ActionRepoImpl
import com.myth.journi.data.GoalRepoImpl
import com.myth.journi.data.PomodoroRepoImpl
import com.myth.journi.data.TaskRepoImpl
import com.myth.journi.domain.repository.ActionRepo
import com.myth.journi.domain.repository.GoalRepo
import com.myth.journi.domain.repository.PomodoroRepo
import com.myth.journi.domain.repository.TaskRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class Interfaces {

    @Binds
    abstract fun actionRepo(actionRepoImpl: ActionRepoImpl): ActionRepo

    @Binds
    abstract fun goalRepo(goalRepoImpl: GoalRepoImpl): GoalRepo

    @Binds
    abstract fun pomodoroRepo(pomodoroRepoImpl: PomodoroRepoImpl): PomodoroRepo

    @Binds
    abstract fun taskRepo(taskRepoImpl: TaskRepoImpl): TaskRepo
}