package com.example.instagramhome

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.instagramhome.ui.theme.InstagramHomeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstagramHomeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginFlow()
                }
            }
        }
    }
}

@Composable
fun LoginFlow(authViewModel: AuthViewModel = viewModel()) {
    val user = authViewModel.user.value
    val isCheckingAuth = authViewModel.isCheckingAuth.value
    LaunchedEffect(user) {
        Log.d("MainActivity", "User state changed: ${if (user != null) "Logged in (${user.email})" else "Logged out"}")
    }

    when {
        isCheckingAuth -> {
            Log.d("MainActivity", "Checking authentication state...")
            LoadingScreen()
        }
        user != null -> {
            Log.d("MainActivity", "User is logged in - Showing InstagramHomeScreen")
            InstagramHomeScreen(authViewModel = authViewModel)
        }
        else -> {
            Log.d("MainActivity", "User not logged in - Showing LoginScreen")
            LoginScreen(
                onLoginSuccess = {
                },
                onSignUpClick = {
                    Log.d("MainActivity", "Sign up via Google Sign-In")
                },
                authViewModel = authViewModel
            )
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "STRMLY",
                fontSize = 48.sp,
                fontWeight = FontWeight.Light,
                color = Color.Black,
                letterSpacing = 2.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            CircularProgressIndicator(
                color = Color(0xFF0095F6),
                modifier = Modifier.size(32.dp),
                strokeWidth = 3.dp
            )
        }
    }
}
