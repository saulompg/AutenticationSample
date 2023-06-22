package com.example.autenticationsample.navigation
sealed class Screen(val route : String) {
    object LoginScreen : Screen(route = "login_screen")
    object SignUpScreen : Screen(route = "sign_up_screen")
    object TermsAndConditionsScreen : Screen(route = "terms_and_conditions_screen")
    object PrivacyPolicyScreen : Screen(route = "privacy_policy_screen")
    object HomeScreen : Screen(route = "home_screen")
}