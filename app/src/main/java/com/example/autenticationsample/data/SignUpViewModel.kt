package com.example.autenticationsample.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.autenticationsample.data.rules.Validator
import com.example.autenticationsample.navigation.Screen
import com.google.firebase.auth.FirebaseAuth


class SignUpViewModel(var navController : NavController): ViewModel() {

    companion object {
        private val TAG = SignUpViewModel::class.simpleName
    }

    var signUpUIState = mutableStateOf(SignUpUIState())

    var signUpInProgress = mutableStateOf(false)

    fun onEvent(event : SignUpUIEvent) {

        when(event) {
            is SignUpUIEvent.FirstNameChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    firstName = event.firstName,
                    firstNameError = true
                )
            }
            is SignUpUIEvent.LastNameChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    lastName = event.lastName,
                    lastNameError = true
                )
            }
            is SignUpUIEvent.EmailChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    email = event.email,
                    emailError = true
                )
            }
            is SignUpUIEvent.PasswordChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    password = event.password,
                    passwordError = true
                )
            }
            is SignUpUIEvent.SignUpButtonClicked -> {
                signUp()
            }
            is SignUpUIEvent.PrivacyPolicyCheckBoxClicked -> {
                signUpUIState.value = signUpUIState.value.copy(
                    privacyPolicyAccepted = event.status
                )
            }
            else -> {}
        }

    }

    private fun validateDataWithRules(): Boolean {

        val firstNameResult = Validator.validateFirstName(
            firstName = signUpUIState.value.firstName
        )
        val lastNameResult = Validator.validateLastName(
            lastName = signUpUIState.value.lastName
        )
        val emailResult = Validator.validateEmail(
            email = signUpUIState.value.email
        )
        val passwordResult = Validator.validatePassword(
            password = signUpUIState.value.password
        )
        val privacyPolicyResult = Validator.validatePrivacyPolicy(
            privacyPolicyAccepted = signUpUIState.value.privacyPolicyAccepted
        )

        Log.d(TAG, "inside_validateDataWithRules")
        Log.d(TAG, "first_name::$firstNameResult")
        Log.d(TAG, "last_name::$lastNameResult")
        Log.d(TAG, "email::$emailResult")
        Log.d(TAG, "password::$passwordResult")
        Log.d(TAG, "privacyPolicyAccepted::$privacyPolicyResult")

        signUpUIState.value = signUpUIState.value.copy(
            firstNameError = firstNameResult.status,
            lastNameError = lastNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
            privacyPolicyError = privacyPolicyResult.status
        )

        return firstNameResult.status && lastNameResult.status &&
                emailResult.status && passwordResult.status && privacyPolicyResult.status

    }

    private fun signUp() {
        Log.d(TAG, "inside_signUp")
        if(validateDataWithRules()) {
            createUserInFirebase(
                email = signUpUIState.value.email,
                password = signUpUIState.value.password
            )
        }
    }

    private fun createUserInFirebase(email: String, password: String) {

        signUpInProgress.value = true

        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                signUpInProgress.value = false
                if(task.isSuccessful) {
                    Log.d(TAG, "signUp::success")
                    navController.navigate(route = Screen.LoginScreen.route) {
                        popUpTo(Screen.SignUpScreen.route) {
                            inclusive = true
                        }
                    }
                }
            }
            .addOnFailureListener {task ->
                signUpInProgress.value = false
                Log.d(TAG, "signUp::failure")
                Log.d(TAG, "Message::${task.localizedMessage}")
            }
    }

}
