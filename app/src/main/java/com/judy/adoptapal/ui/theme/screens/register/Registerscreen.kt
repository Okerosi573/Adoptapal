package com.judy.adoptapal.ui.theme.screens.register

import androidx.compose.foundation.shape.RoundedCornerShape
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.judy.adoptapal.R
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.judy.adoptapal.data.AuthViewModel
import com.judy.adoptapal.navigation.ROUTE_HOME
import com.judy.adoptapal.navigation.ROUTE_LOGIN
import com.judy.adoptapal.navigation.ROUTE_REGISTER
import com.judy.adoptapal.ui.theme.bluu
import com.judy.adoptapal.ui.theme.ivoryGold

@Composable
fun Register_Screen(
    navController: NavHostController,
    viewModel: AuthViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var fname by remember { mutableStateOf(TextFieldValue("")) }
    var lname by remember { mutableStateOf(TextFieldValue("")) }
    var confirm by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by remember { mutableStateOf(false) }


    val context = LocalContext.current
    val registerState = viewModel.registerState.collectAsState()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(bluu)
            .padding(16.dp)
    ) {
        Text(
            "Sign up",
            color = Color.White,
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = fname,
            onValueChange = { fname = it },
            label = { Text("First Name", color = Color.White) },
            textStyle = TextStyle(color = Color.White),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = lname,
            onValueChange = { lname = it },
            label = { Text("Last Name", color = Color.White) },
            textStyle = TextStyle(color = Color.White),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email", color = Color.White) },
            leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Email Icon")},
            textStyle = TextStyle(color = Color.White),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password", color = Color.White) },
            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Password Icon")},
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            textStyle = TextStyle(color = Color.White),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            trailingIcon = {
                val image = if (passwordVisible) painterResource(R.drawable.visibilityoff) else painterResource(R.drawable.visibility)
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(image, contentDescription = if (passwordVisible) "Hide Password" else "Show Password")
                }
            }
        )
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = confirm,
            onValueChange = { confirm = it },
            label = { Text("Confirm Password", color = Color.White) },
            trailingIcon = {
                val image = if (passwordVisible) painterResource(R.drawable.visibilityoff) else painterResource(R.drawable.visibility)
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(image, contentDescription = if (passwordVisible) "Hide Password" else "Show Password")
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            textStyle = TextStyle(color = Color.White),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (password.text != confirm.text) {
                    Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.register(
                        email = email.text.trim(),
                        password = password.text.trim(),
                        firstName = fname.text.trim(),
                        lastName = lname.text.trim()
                    )
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDCE3EA)),
            shape = RoundedCornerShape(25.dp),
            modifier = Modifier
                .width(250.dp)
                .height(50.dp)
        ) {
            if (registerState.value.isLoading) {
                CircularProgressIndicator(color = bluu, strokeWidth = 2.dp)
            } else {
                Text("Click to sign up", color = bluu, fontSize = 18.sp)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            "Already have an account? Click to login",
            color = ivoryGold,
            fontSize = 16.sp,
            modifier = Modifier.clickable {
                navController.navigate(ROUTE_LOGIN)
            }
        )
    }

    // Handle side effects like showing Toast or navigation
    LaunchedEffect(registerState.value) {
        registerState.value.errorMessage?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            viewModel.clearRegisterState()
        }
        if (registerState.value.isSuccess) {
            Toast.makeText(context, "Registered successfully", Toast.LENGTH_SHORT).show()
            navController.navigate(ROUTE_HOME) {
                popUpTo(ROUTE_REGISTER) { inclusive = true }
            }
            viewModel.clearRegisterState()
        }
    }
}




