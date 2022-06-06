package com.anastasiyayuragina.mealplanner.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ButtonGreen(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface)
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = text.uppercase(),
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.button
        )
    }
}

@Composable
fun FloatButton(modifier: Modifier = Modifier, iconRes: Int, onClick: () -> Unit) {
    FloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        backgroundColor = MaterialTheme.colors.secondary,
        contentColor = MaterialTheme.colors.onSecondary
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null
        )
    }
}