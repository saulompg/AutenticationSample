package com.example.autenticationsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.autenticationsample.navigation.SetupNavGraph

class MainActivity : ComponentActivity() {

    // Declaração de objetos
    private lateinit var navController : NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            navController = rememberNavController()
            SetupNavGraph(navController = navController)

        }

    }

}
