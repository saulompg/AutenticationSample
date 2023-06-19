package com.example.autenticationsample.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.autenticationsample.screens.*

@Composable
fun SetupNavGraph(
    navController : NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route
    ) {

        composable(
            route = Screen.LoginScreen.route
        ) {
            LoginScreen(navController = navController)
        }

        composable(
            route = Screen.SignUpScreen.route
        ) {
            SignUpScreen(navController = navController)
        }

        composable(
            route = Screen.TermsAndConditionsScreen.route
        ) {
            TermsAndConditionsScreen(navController = navController)
        }

        composable(
            route = Screen.HomeScreen.route
        ) {
            HomeScreen(navController = navController)
        }

    }
}