package com.anastasiyayuragina.mealplanner.base

import com.anastasiyayuragina.mealplanner.data.AppException
import com.anastasiyayuragina.mealplanner.ui.navigation.Destination

sealed class Event {
    data class NavigateUp(val route: String? = null) : Event()
    data class NavigateUpWithResult(
        val route: String? = null,
        val key: String,
        val result: Any
    ) : Event()
    data class Navigate(val destination: Destination) : Event()
    data class ErrorDialog(val exception: AppException) : Event()
}
