package com.myth.journi.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.myth.journi.common.utils.Screen
import com.myth.journi.presentation.screens.pomodoro.PomodoroScreen
import com.myth.journi.presentation.screens.task.ActionsListScreen
import com.myth.journi.presentation.screens.task.CreateGoalScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = Screen.ActionsListScreen.route
    ) {
        composable(Screen.ActionsListScreen.route) {
            ActionsListScreen(navController = navController)
        }
        composable(Screen.GoalCreationScreen.route) {
            CreateGoalScreen(navController = navController)
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