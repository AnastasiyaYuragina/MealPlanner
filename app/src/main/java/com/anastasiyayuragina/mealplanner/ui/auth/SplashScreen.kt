package com.anastasiyayuragina.mealplanner.ui.auth

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.anastasiyayuragina.mealplanner.R
import com.anastasiyayuragina.mealplanner.ui.navigation.AuthDestination
import com.anastasiyayuragina.mealplanner.ui.navigation.navigateTo
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val SPLASH_DURATION = 1500L

@Composable
fun SplashScreen(navController: NavController) {
    val activity = LocalContext.current as Activity
    val scope = rememberCoroutineScope()

    BackHandler {
        activity.finish()
    }

    LaunchedEffect(Unit) {
        scope.launch {
            delay(SPLASH_DURATION)
            navController.navigateTo(AuthDestination.AuthScreen)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(108.dp)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.ic_app),
                tint = MaterialTheme.colors.primaryVariant,
                contentDescription = "Meal planer icon"
            )
            Text(
                modifier = Modifier.fillMaxWidth().padding(24.dp),
                text = stringResource(id = R.string.app_name),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.primaryVariant,
                style = MaterialTheme.typography.h3
            )
        }

    }
}