package com.myth.journi.domain.usecase

import android.util.Log
import com.myth.journi.domain.model.Action
import com.myth.journi.domain.model.Goal
import com.myth.journi.domain.model.Pomodoro
import com.myth.journi.domain.model.Task
import com.myth.journi.domain.repository.ActionRepo
import com.myth.journi.domain.repository.GoalRepo
import com.myth.journi.domain.repository.PomodoroRepo
import com.myth.journi.domain.repository.TaskRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject
import kotlin.time.Duration.Companion.minutes
import kotlin.time.DurationUnit

class GoalUseCase @Inject constructor(
    private val goalRepo: GoalRepo,
    private val actionRepo: ActionRepo,
    private val pomodoroRepo: PomodoroRepo,
    private val taskRepo: TaskRepo
) {
    suspend fun saveGoal(
        goal: Goal,
        category: String,
        startDate: Long,
        endDate: Long,
        startTimeHour: Int,
        startTimeMinute: Int,
        endTimeHour: Int,
        endTimeMinute: Int,
        tasks: List<String>,
        onSuccess: (Boolean, String?) -> Unit
    ) {
        try {
            var actions: Long = 0
            val goalId = goalRepo.saveGoal(goal)
            if (goalId > 0) {
                val actionData = Action(
                    id = 0,
                    goalId = goalId,
                    title = goal.title,
                    category = category,
                    total = tasks.size,
                    completed = 0,
                    startDate = startDate,
                    endDate = endDate,
                    timeBlockStart = String.format(
                        "%02d%02d",
                        startTimeHour,
                        startTimeMinute
                    ).toLong(),
                    timeBlockEnd = String.format(
                        "%02d%02d",
                        endTimeHour,
                        endTimeMinute
                    ).toLong()
                )
                actions = actionRepo.addAction(actionData)
            }

            if (actions > 0) {
                val taskData = tasks.mapIndexed { index, description ->
                    Task(
                        id = index.toLong(),
                        actionId = actions,
                        done = false,
                        description = description
                    )
                }
                val pomodoro = Pomodoro(
                    id = 0,
                    actionId = actions,
                    runs = 0,
                    duration = 25.minutes.toLong(DurationUnit.MILLISECONDS),
                    shortRestDuration = 5.minutes.toLong(DurationUnit.MILLISECONDS),
                    longRestDuration = 15.minutes.toLong(DurationUnit.MILLISECONDS),
                    setsBeforeLongRest = 4
                )
                taskRepo.addAllTasks(taskData)
                pomodoroRepo.addPomodoroSettings(pomodoro)
            }
            onSuccess(true, null)

        } catch (e: Exception) {
            onSuccess(false, e.message)
        }
    }

    suspend fun updateGoal(goal: Goal, onSuccess: (Boolean, String?) -> Unit) {
        try {
            goalRepo.updateGoal(goal)
            onSuccess(true, "Goal updated")
        } catch (e: Exception) {
            onSuccess(false, e.message)
        }
    }

    suspend fun deleteGoal(goal: Goal, onSuccess: (Boolean, String?) -> Unit) {
        try {
            goalRepo.deleteGoal(goal)
            onSuccess(true, "Goal deleted")
        } catch (e: Exception) {
            onSuccess(false, e.message)
        }
    }

    suspend fun deleteGoals(goals: List<Goal>, onSuccess: (Boolean, String?) -> Unit) {
        try {
            goalRepo.deleteGoals(goals)
            onSuccess(true, "Goals Deleted")
        } catch (e: Exception) {
            onSuccess(false, e.message)
        }
    }

    fun getAllGoals(): Flow<List<Goal>> {
        var goals: Flow<List<Goal>> = emptyFlow()
        try {
            goals = goalRepo.getAllGoals()
        } catch (e: Exception) {
            Log.d("Goals Use Case", e.message!!)
        }
        return goals
    }

}