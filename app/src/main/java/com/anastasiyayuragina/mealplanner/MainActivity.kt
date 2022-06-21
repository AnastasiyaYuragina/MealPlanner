package com.anastasiyayuragina.mealplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.anastasiyayuragina.mealplanner.ui.MainViewModel
import com.anastasiyayuragina.mealplanner.ui.navigation.AuthRoutes
import com.anastasiyayuragina.mealplanner.ui.navigation.BottomNavigationBar
import com.anastasiyayuragina.mealplanner.ui.navigation.NavHost
import com.anastasiyayuragina.mealplanner.ui.theme.MealPlannerTheme
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private var currentRoute: String? = null

    private lateinit var oneTabClient: SignInClient
    private lateinit var signInRequest: BeginSignInRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        oneTabClient = Identity.getSignInClient(this)
        signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(getString(R.string.web_client_id_auth))
                    .setFilterByAuthorizedAccounts(true)
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()

        setContent {
            MealPlannerTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                currentRoute = navBackStackEntry?.destination?.route
                val startDestination = remember {
                    AuthRoutes.SPLASH
                }

                Scaffold (
                    bottomBar = {
                        when(currentRoute) {
                            AuthRoutes.AUTH -> BottomNavigationBar(navController = navController)
                        }
                                },
                    content = { innerPadding ->
                        NavHost(
                            modifier = Modifier.padding(innerPadding),
                            navController = navController,
                            startDestination = startDestination
                        )
                    }
                )
            }
        }
    }
}