package com.example.autenticationsample.data

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.autenticationsample.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener

class HomeViewModel(var navController: NavController): ViewModel() {

    companion object {
        private val TAG = HomeViewModel::class.simpleName
    }

    fun logout() {
        FirebaseAuth
            .getInstance()
            .signOut()
        val authStateListener = AuthStateListener {
            if (it.currentUser == null) {
                Log.d(TAG, "signOut::success")
                navController.navigate(Screen.LoginScreen.route) {
                    launchSingleTop = true
                    popUpTo(Screen.HomeScreen.route) {
                        inclusive = true
                    }
                }
            } else {
                Log.d(TAG, "signOut::failure")
            }
        }
        FirebaseAuth.getInstance().addAuthStateListener(authStateListener)
    }

}