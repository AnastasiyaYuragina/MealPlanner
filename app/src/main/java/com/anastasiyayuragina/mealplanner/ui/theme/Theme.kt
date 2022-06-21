package com.anastasiyayuragina.mealplanner.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Teal,
    primaryVariant = PersianGreen,
    secondary = Blue,
    secondaryVariant = ToreaBay,
    background = CodGray,
    surface = CodGray,
    onPrimary = CodGray,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    error = Red
)

private val LightColorPalette = lightColors(
    primary = Teal,
    primaryVariant = PersianGreen,
    secondary = Blue,
    secondaryVariant = ToreaBay,
    background = Color.White,
    surface = Color.White,
    onPrimary = CodGray,
    onSecondary = Color.White,
    onBackground = BastilleGrey,
    onSurface = BastilleGrey,
    error = Red
)

@Composable
fun MealPlannerTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}