package com.anastasiyayuragina.mealplanner.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.anastasiyayuragina.mealplanner.ui.auth.AuthScreen
import com.anastasiyayuragina.mealplanner.ui.auth.AuthViewModel
import com.anastasiyayuragina.mealplanner.ui.auth.SplashScreen

object AuthRoutes {
    const val SPLASH = "splash_route"
    const val AUTH = "auth_route"
}

fun NavGraphBuilder.authNavigation(
    navController: NavController
) {
    composable(route = AuthRoutes.SPLASH) {
        SplashScreen(navController = navController)
    }

    composable(
        route = AuthRoutes.AUTH
    ) {
        val viewModel: AuthViewModel = hiltViewModel()

        AuthScreen(navController = navController, viewModel = viewModel)
    }
}