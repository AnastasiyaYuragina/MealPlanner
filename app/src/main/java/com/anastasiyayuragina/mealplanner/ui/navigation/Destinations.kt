package com.anastasiyayuragina.mealplanner.ui.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController

sealed class Destination(
    val route: String,
    val params: Map<String, Any?> = mapOf()
)

object AuthDestination {
    object SplashScreen : Destination(AuthRoutes.SPLASH)

    object AuthScreen : Destination(AuthRoutes.AUTH)
}

fun NavController.navigateTo(destination: Destination) {
    navigate(destination.route)
    backQueue.last().putArguments(destination)
}

fun NavBackStackEntry.putArguments(destination: Destination) =
    arguments?.putAll(bundleOf(*destination.params.toList().toTypedArray()))

