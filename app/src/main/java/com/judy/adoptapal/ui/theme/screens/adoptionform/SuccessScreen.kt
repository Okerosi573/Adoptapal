package com.judy.adoptapal.ui.theme.screens.adoptionform

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.judy.adoptapal.R
import com.judy.adoptapal.navigation.ROUTE_HOME
import com.judy.adoptapal.navigation.ROUTE_PETCATEGORYSECTION
import com.judy.adoptapal.navigation.ROUTE_SUCCESS
import java.net.URLEncoder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuccessScreen(navController: NavHostController) {
    val context = LocalContext.current
    val phoneNumber = "+254769336675"
    val message = "Hello, I just submitted an adoption form and would like more info."
    val encodedMessage = URLEncoder.encode(message, "UTF-8")
    val url = "https://wa.me/$phoneNumber?text=$encodedMessage"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Submission Successful") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF4CAF50),
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(WindowInsets.systemBars.asPaddingValues())
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Success",
                    tint = Color(0xFF4CAF50),
                    modifier = Modifier.size(80.dp)
                )

                Text("Thank you!", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                Text(
                    text = "Your adoption request has been submitted. Our team will contact you within 24–48 hours.",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Button(onClick = {
                        navController.navigate(ROUTE_HOME) {
                            popUpTo(ROUTE_SUCCESS) { inclusive = true }
                        }
                    }) {
                        Text("Back to Home")
                    }

                    Button(onClick = {
                        navController.navigate(ROUTE_PETCATEGORYSECTION) // Replace with your actual pet list route
                    }) {
                        Text("View More Pets")
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedButton(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        try {
                            context.startActivity(intent)
                        } catch (e: ActivityNotFoundException) {
                            Toast.makeText(context, "WhatsApp not installed.", Toast.LENGTH_SHORT).show()
                        }
                    },
                    border = BorderStroke(1.dp, Color(0xFF25D366))
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_whatsapp),
                        contentDescription = "Contact WhatsApp",
                        tint = Color(0xFF25D366),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Contact Us on WhatsApp", color = Color(0xFF25D366))
                }
            }
        }
    }
}

@Preview
@Composable
private fun Successprev() {
    SuccessScreen(rememberNavController())
}