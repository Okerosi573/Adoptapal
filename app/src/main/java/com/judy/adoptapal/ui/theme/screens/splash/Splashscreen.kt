package com.judy.adoptapal.ui.theme.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.judy.adoptapal.R
import com.judy.adoptapal.navigation.ROUTE_LOGIN
import com.judy.adoptapal.ui.theme.bluu
import kotlinx.coroutines.delay

@Composable
fun Splash_Screen(navController: NavHostController) {
    // Run this once when the SplashScreen is composed
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate(ROUTE_LOGIN) {
            popUpTo("splash") { inclusive = true } // optional: remove splash from backstack
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bluu),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.spllash),
            contentDescription = "splash",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        )
    }
}

@Preview
@Composable
private fun Splashprev() {
    Splash_Screen(rememberNavController())
    
}


