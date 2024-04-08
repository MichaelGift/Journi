package com.myth.journi.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.myth.journi.common.utils.Screen
import com.myth.journi.presentation.screens.pomodoro.PomodoroScreen
import com.myth.journi.presentation.screens.task.CreateGoalScreen
import com.myth.journi.presentation.screens.task.ActionsListScreen
import com.myth.journi.ui.theme.JourniTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JourniTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
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
            }
        }
    }
}