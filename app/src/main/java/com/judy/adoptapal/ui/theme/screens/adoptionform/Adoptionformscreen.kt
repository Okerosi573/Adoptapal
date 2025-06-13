package com.judy.adoptapal.ui.theme.screens.adoptionform

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.judy.adoptapal.data.AdoptionFormViewModel
import com.judy.adoptapal.navigation.ROUTE_SUCCESS
import com.judy.adoptapal.ui.theme.ivoryGold
import com.judy.adoptapal.ui.theme.nightSky


@Composable
fun AdoptionForm(viewModel: AdoptionFormViewModel, onSubmit: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(nightSky)
            .padding(13.dp)
            .padding(WindowInsets.systemBars.asPaddingValues()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val textStyle = TextStyle(color = Color.White)

        OutlinedTextField(
            value = viewModel.fullName,
            onValueChange = { viewModel.fullName = it },
            label = { Text("Full Name", color = Color.White) },
            textStyle = textStyle,

            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = viewModel.phoneNumber,
            onValueChange = { viewModel.phoneNumber = it },
            label = { Text("Phone Number", color = Color.White) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
            textStyle = textStyle,

            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = viewModel.location,
            onValueChange = { viewModel.location = it },
            label = { Text("Address", color = Color.White) },
            textStyle = textStyle,

            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = viewModel.homeStatus,
            onValueChange = { viewModel.homeStatus = it },
            label = { Text("Rent or Own", color = Color.White) },
            textStyle = textStyle,

            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = viewModel.reason,
            onValueChange = { viewModel.reason = it },
            label = { Text("Why adopt this pet?", color = Color.White) },
            textStyle = textStyle,

            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = viewModel.petStayLocation,
            onValueChange = { viewModel.petStayLocation = it },
            label = { Text("Where will pet stay?", color = Color.White) },
            textStyle = textStyle,

            modifier = Modifier.fillMaxWidth()
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Owned a pet before?", color = Color.White)
            Spacer(Modifier.width(8.dp))
            Switch(
                checked = viewModel.hasOwnedPet,
                onCheckedChange = { viewModel.hasOwnedPet = it }
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Can afford vet & food?", color = Color.White)
            Spacer(Modifier.width(8.dp))
            Switch(
                checked = viewModel.financiallyAble,
                onCheckedChange = { viewModel.financiallyAble = it }
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = viewModel.agreeReturn,
                onCheckedChange = { viewModel.agreeReturn = it }
            )
            Text("Agree to return pet if needed", color = Color.White)
        }

        Text(
            text = "Kindly note that there is an adoption fee of Ksh.4500 for all dogs and Ksh.3500 for all cats",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.SemiBold,
            color = ivoryGold
        )

        Button(
            onClick = onSubmit,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit Adoption Request")
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdoptionForm_Screen(navController: NavHostController, petId: String? = null) {
    val context = LocalContext.current
    val user = FirebaseAuth.getInstance().currentUser
    val userId = user?.uid ?: "unknown_user"
    val viewModel = remember { AdoptionFormViewModel() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Adoption Form") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2196F3),
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            AdoptionForm(viewModel = viewModel) {
                if (!viewModel.validate()) {
                    Toast.makeText(context, "Please complete all required fields.", Toast.LENGTH_SHORT).show()
                    return@AdoptionForm
                }

                // Navigate immediately to success screen
                navController.navigate(ROUTE_SUCCESS) {
                    popUpTo("adoptionForm") { inclusive = true }
                }

                // Submit data in the background
                viewModel.submit(
                    userId = userId,
                    petId = petId,
                    onSuccess = {},
                    onError = {}
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AdoptionFormPreview() {
    AdoptionForm_Screen(navController = rememberNavController())
}
