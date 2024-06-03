package com.myth.journi.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.myth.journi.common.utils.Screen
import com.myth.journi.presentation.screens.pomodoro.PomodoroScreen
import com.myth.journi.presentation.screens.task.ActionsListScreen
import com.myth.journi.presentation.screens.task.ActionsViewModel
import com.myth.journi.presentation.screens.task.CreateGoalScreen
import com.myth.journi.presentation.screens.task.GoalsViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val actionsViewModel: ActionsViewModel = hiltViewModel()
    val goalsViewModel: GoalsViewModel = hiltViewModel()


    NavHost(
        navController = navController, startDestination = Screen.ActionsListScreen.route
    ) {
        composable(Screen.ActionsListScreen.route) {
            ActionsListScreen(
                navigate = { navController.navigate(it) },
                actions = actionsViewModel.actions.value
            )
        }

        composable(Screen.GoalCreationScreen.route) {
            CreateGoalScreen(
                navigateBack = { navController.navigateUp() },
                goalEvent = goalsViewModel::onEvent
            )
        }

        composable(
            route = Screen.PomodoroScreen.route + "?actionId={actionId}",
            arguments = listOf(
                navArgument(name = "actionId") {
                    type = NavType.LongType
                    defaultValue = -1
                },
            )
        ) {
            PomodoroScreen(navController = navController)
        }
    }
}