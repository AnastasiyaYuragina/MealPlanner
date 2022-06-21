package com.anastasiyayuragina.mealplanner.base

sealed class ScreenState<out T> {
    object Idle : ScreenState<Nothing>()
    object Loading : ScreenState<Nothing>()
    object NoConnection: ScreenState<Nothing>()
    data class Success<T>(val data: T) : ScreenState<T>()
}
