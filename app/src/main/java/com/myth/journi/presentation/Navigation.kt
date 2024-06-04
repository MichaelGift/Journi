package com.myth.journi.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.myth.journi.common.utils.Screen
import com.myth.journi.presentation.screens.pomodoro.PomodoroScreen
import com.myth.journi.presentation.screens.pomodoro.PomodoroViewModel
import com.myth.journi.presentation.screens.pomodoro.TasksViewModel
import com.myth.journi.presentation.screens.task.ActionsListScreen
import com.myth.journi.presentation.screens.task.ActionsViewModel
import com.myth.journi.presentation.screens.task.CreateGoalScreen
import com.myth.journi.presentation.screens.task.GoalsViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val actionsVM: ActionsViewModel = hiltViewModel()
    val goalsVM: GoalsViewModel = hiltViewModel()
    val tasksVM: TasksViewModel = hiltViewModel()
    val pomodoroVM: PomodoroViewModel = hiltViewModel()


    NavHost(
        navController = navController, startDestination = Screen.ActionsListScreen.route
    ) {
        composable(Screen.ActionsListScreen.route) {
            ActionsListScreen(
                navigate = { navController.navigate(it) },
                actionsState = actionsVM.actionState,
                pomodoroEvent = pomodoroVM::onEvent,
                taskEvent = tasksVM::onEvent
            )
        }

        composable(Screen.GoalCreationScreen.route) {
            CreateGoalScreen(
                navigateBack = { navController.navigateUp() },
                goalEvent = goalsVM::onEvent
            )
        }

        composable(route = Screen.PomodoroScreen.route) {
            PomodoroScreen(
                pomodoroState = pomodoroVM.pomodoroState,
                taskState = tasksVM.taskState,
                taskEvent = tasksVM::onEvent,
                onBack = { navController.navigateUp() })
        }
    }
}