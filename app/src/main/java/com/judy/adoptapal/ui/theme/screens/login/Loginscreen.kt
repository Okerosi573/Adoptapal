package com.judy.adoptapal.ui.theme.screens.login



import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.judy.adoptapal.R
import com.judy.adoptapal.data.AuthViewModel
import com.judy.adoptapal.navigation.ROUTE_HOME
import com.judy.adoptapal.navigation.ROUTE_LOGIN
import com.judy.adoptapal.navigation.ROUTE_REGISTER
import com.judy.adoptapal.ui.theme.bluu
import com.judy.adoptapal.ui.theme.button
import com.judy.adoptapal.ui.theme.ivoryGold

@Composable
fun Login_Screen(
    navController: NavHostController,
    viewModel: AuthViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    val context = LocalContext.current
    val loginState = viewModel.loginState.collectAsState()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(bluu)
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.paw2),
            contentDescription = "paw",
            modifier = Modifier.size(150.dp)
        )

        Text(
            "Welcome Back :)",
            color = Color.White,
            fontSize = 25.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Enter email", color = Color.White, fontSize = 18.sp) },
            leadingIcon = {Icon(Icons.Filled.Email, contentDescription = "Email icon")},
            textStyle = LocalTextStyle.current.copy(color = Color.White),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(),


        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            trailingIcon = {
                val image = if (passwordVisible) painterResource(R.drawable.visibilityoff) else painterResource(R.drawable.visibility)
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(image, contentDescription = if (passwordVisible) "Hide Password" else "Show Password")
                }
            },
            label = { Text("Enter password", color = Color.White, fontSize = 18.sp) },
            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Password Icon")},
            textStyle = LocalTextStyle.current.copy(color = Color.White),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()

        )

        Spacer(modifier = Modifier.height(60.dp))

        Button(
            onClick = { viewModel.login(email.text.trim(), password.text.trim()) },
            colors = ButtonDefaults.buttonColors(button),
            modifier = Modifier
                .width(250.dp)
                .height(50.dp),
            shape = RoundedCornerShape(25.dp)
        ) {
            if (loginState.value.isLoading) {
                CircularProgressIndicator(color = Color.Black, strokeWidth = 2.dp)
            } else {
                Text(
                    "Click to login",
                    color = Color.Black,
                    fontSize = 25.sp,
                    fontFamily = FontFamily.Default
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            "Don't have an account? Click to Register",
            fontSize = 16.sp,
            color = ivoryGold,
            modifier = Modifier.clickable {
                navController.navigate(ROUTE_REGISTER)
            }
        )
    }

    // Handle side effects like showing Toast or navigation
    LaunchedEffect(loginState.value) {
        loginState.value.errorMessage?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            viewModel.clearLoginState()
        }
        if (loginState.value.isSuccess) {
            Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
            navController.navigate(ROUTE_HOME) {
                popUpTo(ROUTE_LOGIN) { inclusive = true }
            }
            viewModel.clearLoginState()
        }
    }
}

