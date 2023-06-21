package com.example.autenticationsample.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.autenticationsample.data.rules.Validator
import com.example.autenticationsample.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel(var navController : NavController) : ViewModel() {

    companion object {
        private val TAG = LoginViewModel::class.simpleName
    }

    var loginUIState = mutableStateOf(LoginUIState())

    var loginInProgress = mutableStateOf(false)

    fun onEvent(event : LoginUIEvent) {

        when(event) {
            is LoginUIEvent.EmailChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    email = event.email,
                    emailError = true
                )
            }
            is LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    password = event.password,
                    passwordError = true
                )
            }
            is LoginUIEvent.LoginButtonClicked -> {
                login()
            }
        }

    }

    private fun validateDataWithRules(): Boolean {

        val emailResult = Validator.validateEmail(
            email = loginUIState.value.email
        )
        val passwordResult = Validator.validatePassword(
            password = loginUIState.value.password
        )

        Log.d(TAG, "inside_validateDataWithRules")
        Log.d(TAG, "email::$emailResult")
        Log.d(TAG, "password::$passwordResult")

        loginUIState.value = loginUIState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status,
        )

        return emailResult.status && passwordResult.status

    }

    private fun login() {

        Log.d(TAG, "inside_login")
        if(validateDataWithRules()) {
            signInWithFirebase(
                email = loginUIState.value.email,
                password = loginUIState.value.password
            )
        }

    }

    private fun signInWithFirebase(email: String, password: String) {

        loginInProgress.value = true

        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                loginInProgress.value = false
                if(task.isSuccessful) {
                    Log.d(TAG, "login::success")
                    navController.navigate(route = Screen.HomeScreen.route) {
                        popUpTo(Screen.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                }
            }
            .addOnFailureListener {task ->
                loginInProgress.value = false
                Log.d(TAG, "login::failure")
                Log.d(TAG, "Message::${task.localizedMessage}")
            }
    }

}