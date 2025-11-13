package com.example.medalert.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.medalert.ui.home.HomeScreen
import com.example.medalert.ui.reminder.ReminderEntryScreen

@Composable
fun MedAlertNavGraph(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(navController = navController)
        }

        composable(route = ReminderEntryDestination.route) {
            ReminderEntryScreen(
                navigateBack = { navController.navigateUp() },
            )
        }
    }
}