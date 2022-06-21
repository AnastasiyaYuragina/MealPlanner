package com.anastasiyayuragina.mealplanner.ui.auth

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.events
import androidx.navigation.NavController
import com.anastasiyayuragina.mealplanner.R
import com.anastasiyayuragina.mealplanner.utils.HandleDefaultEvents
import com.google.android.gms.common.SignInButton

@Composable
fun AuthScreen(
    navController: NavController,
    viewModel: AuthViewModel
) {
    val activity = LocalContext.current as Activity

    BackHandler {
        activity.finish()
    }
    HandleDefaultEvents(
        events = viewModel.events,
        navController = navController
    )

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
                    .align(CenterHorizontally),
                painter = painterResource(id = R.drawable.ic_app),
                tint = MaterialTheme.colors.primaryVariant,
                contentDescription = stringResource(id = R.string.app_name)
            )
            Text(
                modifier = Modifier.fillMaxWidth().padding(24.dp),
                text = stringResource(
                    id = R.string.welcome_to,
                    stringResource(id = R.string.app_name)
                ),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.primaryVariant,
                style = MaterialTheme.typography.h3
            )
        }
        
    }
}