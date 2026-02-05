package com.example.myapplication2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.myapplication2.ui.taskedit.TaskEditScreen
import com.example.myapplication2.ui.taskslist.TasksListScreen

sealed class Screen(val route: String) {
    object TasksList : Screen("tasks_list")
    object TaskEdit : Screen("task_edit/{taskId}") {
        fun createRoute(taskId: Int) = "task_edit/$taskId"
    }
}

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.TasksList.route
    ) {

        composable(Screen.TasksList.route) {
            TasksListScreen(
                onTaskClick = { taskId ->
                    navController.navigate(Screen.TaskEdit.createRoute(taskId))
                }
            )
        }

        composable(
            route = Screen.TaskEdit.route,
            arguments = listOf(
                navArgument("taskId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt("taskId") ?: 0
            TaskEditScreen(
                taskId = taskId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}