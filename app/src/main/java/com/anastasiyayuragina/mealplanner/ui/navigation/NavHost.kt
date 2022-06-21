package com.anastasiyayuragina.mealplanner.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun NavHost(
    modifier: Modifier,
    navController: NavHostController,
    startDestination: String
) {
    androidx.navigation.compose.NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        authNavigation(navController = navController)
    }
}