package com.example.autenticationsample

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.autenticationsample.navigation.SetupNavGraph
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {

    // Declaração de objetos
    private lateinit var navController : NavHostController
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            navController = rememberNavController()
            SetupNavGraph(navController = navController)

        }

        auth = Firebase.auth

    }

    private fun createUserEmailPassword(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if(task.isSuccessful) {
                Log.d(TAG, "createUserWithEmailAndPassword:Success")
                val user = auth.currentUser
            } else {
                Log.d(TAG, "createUserWithEmailAndPassword:Failure", task.exception)
                Toast.makeText(baseContext, "Autentication Failure", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun sighInWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if(task.isSuccessful) {
                Log.d(TAG, "signInUserWithEmailAndPassword:Success")
                val user = auth.currentUser
            } else {
                Log.d(TAG, "signInUserWithEmailAndPassword:Failure", task.exception)
                Toast.makeText(baseContext, "Autentication Failure", Toast.LENGTH_SHORT)
            }
        }
    }

    companion object {
        private var TAG = "EmailAndPassword"
    }
}
