package com.myth.journi.common.utils

sealed class Screen(val route: String) {
    data object ActionsListScreen: Screen("actions_list_screen")
    data object GoalCreationScreen: Screen("goal_creation_screen")
    data object PomodoroScreen : Screen("pomodoro_screen")
}