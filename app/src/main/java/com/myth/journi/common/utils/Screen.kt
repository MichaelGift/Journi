package com.myth.journi.common.utils

sealed class Screen(val route: String) {
    data object TaskList: Screen("task_list")
    data object TaskCreation: Screen("task_creation")
}