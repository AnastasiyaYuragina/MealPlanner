package com.anastasiyayuragina.mealplanner.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.anastasiyayuragina.mealplanner.ui.auth.AuthScreen
import com.anastasiyayuragina.mealplanner.ui.auth.AuthViewModel

object AuthRoutes {
    const val ENTRY = "entry_route"
}

fun NavGraphBuilder.authNavigation(
    navController: NavController
) {
    composable(
        route = AuthRoutes.ENTRY
    ) {
        val viewModel: AuthViewModel = hiltViewModel()

        AuthScreen(navController = navController, viewModel = viewModel)
    }
}