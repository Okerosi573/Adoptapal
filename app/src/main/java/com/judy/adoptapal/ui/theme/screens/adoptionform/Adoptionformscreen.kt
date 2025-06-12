package com.judy.adoptapal.ui.theme.screens.adoptionform

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import com.judy.adoptapal.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.judy.adoptapal.data.submitAdoptionForm
import com.judy.adoptapal.models.AdoptionForm
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import com.judy.adoptapal.navigation.ROUTE_SUCCESS
import com.judy.adoptapal.ui.theme.ivoryGold
import com.judy.adoptapal.ui.theme.nightSky

import java.net.URLEncoder

// Sample color
val softTeal = Color(0xFFE0F7FA)

fun submitAdoptionForm(
    form: AdoptionForm,
    onSuccess: () -> Unit,
    onError: (Exception) -> Unit
) {

    // Firebase Firestore logic (mocked or real)
    val db = com.google.firebase.firestore.FirebaseFirestore.getInstance()
    db.collection("adoptionForms")
        .add(form)
        .addOnSuccessListener { onSuccess() }
        .addOnFailureListener { e -> onError(e) }
}

@Composable
fun AdoptionForm(onSubmit: (String, String, String, String, Boolean, String, String, Boolean, Boolean) -> Unit) {
    var fullName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var homeStatus by remember { mutableStateOf("") }
    var hasOwnedPet by remember { mutableStateOf(false) }
    var reason by remember { mutableStateOf("") }
    var petStayLocation by remember { mutableStateOf("") }
    var financiallyAble by remember { mutableStateOf(false) }
    var agreeReturn by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(nightSky)
            .padding(13.dp)
            .padding(WindowInsets.systemBars.asPaddingValues()) ,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(value = fullName, onValueChange = { fullName = it }, label = { Text("Full Name") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = phoneNumber, onValueChange = { phoneNumber = it }, label = { Text("Phone Number") }, keyboardOptions = KeyboardOptions.Default.copy(keyboardType = androidx.compose.ui.text.input.KeyboardType.Phone), modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = location, onValueChange = { location = it }, label = { Text("Address") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = homeStatus, onValueChange = { homeStatus = it }, label = { Text("Rent or Own") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = reason, onValueChange = { reason = it }, label = { Text("Why adopt this pet?") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = petStayLocation, onValueChange = { petStayLocation = it }, label = { Text("Where will pet stay?") }, modifier = Modifier.fillMaxWidth())

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Owned a pet before?")
            Spacer(Modifier.width(8.dp))
            Switch(checked = hasOwnedPet, onCheckedChange = { hasOwnedPet = it })
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Can afford vet & food?")
            Spacer(Modifier.width(8.dp))
            Switch(checked = financiallyAble, onCheckedChange = { financiallyAble = it })
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = agreeReturn, onCheckedChange = { agreeReturn = it })
            Text("Agree to return pet if needed")
        }


            Text(
                text ="Kindly note that there is an adoption fee of Ksh.4500 for all dogs and Ksh.3500 for all cats",
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.SemiBold,
                color = (ivoryGold),
                textDecoration = TextDecoration.None
            )





        Button(
            onClick = {
                onSubmit(
                    fullName,
                    phoneNumber,
                    location,
                    homeStatus,
                    hasOwnedPet,
                    reason,
                    petStayLocation,
                    financiallyAble,
                    agreeReturn
                )
            },
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

    var isSubmitting by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Adoption Form") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF2196F3), titleContentColor = Color.White)
            )
        },
        bottomBar = {
            WhatsAppContactButton(context)
        }
    ) { paddingValues ->
        if (isSubmitting) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(8.dp))
                Text("Submitting your request...", color = Color.White)
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                AdoptionForm { fullName, phoneNumber, location, homeStatus, hasOwnedPet, reason, petStayLocation, financiallyAble, agreeReturn ->

                    // Optional validation
                    if (fullName.isBlank() || phoneNumber.isBlank() || location.isBlank() || homeStatus.isBlank()) {
                        Toast.makeText(context, "Please fill in all required fields.", Toast.LENGTH_SHORT).show()
                        return@AdoptionForm
                    }

                    val form = AdoptionForm(
                        fullName = fullName,
                        phoneNumber = phoneNumber,
                        location = location,
                        homeStatus = homeStatus,
                        hasOwnedPet = hasOwnedPet,
                        reason = reason,
                        petStayLocation = petStayLocation,
                        financiallyAble = financiallyAble,
                        agreeReturn = agreeReturn,
                        userId = userId,
                        petId = petId,
                        timestamp = System.currentTimeMillis()
                    )

                    isSubmitting = true
                    submitAdoptionForm(
                        form = form,
                        onSuccess = {
                            isSubmitting = false
                            navController.navigate(ROUTE_SUCCESS) {
                                popUpTo("adoptionForm") { inclusive = true }
                            }
                        },
                        onError = { e ->
                            isSubmitting = false
                            Toast.makeText(context, "Error: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
                        }
                    )

                }
            }
        }
    }
}



@Composable
fun WhatsAppContactButton(context: Context) {
    val phoneNumber = "+254769336675" // Format without "+"
    val message = "Hello, I'm interested in adopting a pet!"
    val encodedMessage = URLEncoder.encode(message, "UTF-8")
    val url = "https://wa.me/$phoneNumber?text=$encodedMessage"

    Button(
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            try {
                context.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(context, "WhatsApp not installed.", Toast.LENGTH_SHORT).show()
            }
        },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF25D366)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_whatsapp),
            contentDescription = "Chat",
            tint = Color.White,
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text("Contact Us on WhatsApp", color = Color.White)
    }
}


@Preview(showBackground = true)
@Composable
fun AdoptionFormPreview() {
    AdoptionForm_Screen(navController = rememberNavController())
}
