package com.anastasiyayuragina.mealplanner.utils

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.core.os.bundleOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import com.anastasiyayuragina.mealplanner.base.Event
import com.anastasiyayuragina.mealplanner.data.AppException
import com.anastasiyayuragina.mealplanner.ui.components.ErrorDialog
import com.anastasiyayuragina.mealplanner.ui.navigation.navigateTo
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged

/**
 * A function for handling navigation and error events.
 * @param events a flow of events emitted from a viewModel
 * @param navController NavController for managing navigation between screens
 */
@Composable
fun HandleDefaultEvents(events: SharedFlow<Event>, navController: NavController) {

    val exception = remember { mutableStateOf<AppException?>(null) }

    SetupErrorDialog(exception)

    LaunchedEffect(Unit) {
        events
            .distinctUntilChanged()
            .collect {
                when (it) {
                    is Event.NavigateUp -> {
                        it.route.navigateUp(navController)
                    }
                    is Event.NavigateUpWithResult -> {
                        it.route.navigateUp(navController)
                        navController.currentBackStackEntry?.arguments?.putAll(
                            bundleOf(it.key to it.result)
                        )
                    }
                    is Event.Navigate -> {
                        navController.navigateTo(it.destination)
                    }
                    is Event.ErrorDialog -> {
                        exception.value = it.exception
                    }
                }
            }
    }
}

private fun String?.navigateUp(navController: NavController) {
    if (this == null) {
        navController.navigateUp()
    } else {
        navController.popBackStack(this, false)
    }
}

@Composable
fun SetupErrorDialog(exception: MutableState<AppException?>) {
    exception.value?.let {
       ErrorDialog(exception = it) {
            exception.value = null
        }
    }
}

@Composable
fun Dp.toPx() = (value * LocalDensity.current.density).toInt()

@Composable
fun Lifecycle.observeAsState(): State<Lifecycle.Event> {
    val state = remember { mutableStateOf(Lifecycle.Event.ON_ANY) }
    DisposableEffect(this) {
        val observer = LifecycleEventObserver { _, event ->
            state.value = event
        }
        this@observeAsState.addObserver(observer)
        onDispose {
            this@observeAsState.removeObserver(observer)
        }
    }
    return state
}