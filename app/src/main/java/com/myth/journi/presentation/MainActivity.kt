package com.myth.journi.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.myth.journi.common.utils.Screen
import com.myth.journi.presentation.screens.task.CreateGoalScreen
import com.myth.journi.presentation.screens.task.TaskListScreen
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
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.TaskList.route
                    ) {
                        composable(Screen.TaskList.route) {
                            TaskListScreen(navController = navController)
                        }
                        composable(Screen.TaskCreation.route) {
                            CreateGoalScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}