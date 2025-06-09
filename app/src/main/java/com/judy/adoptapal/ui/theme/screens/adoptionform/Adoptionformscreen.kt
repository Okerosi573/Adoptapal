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
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.judy.adoptapal.data.submitAdoptionForm
import com.judy.adoptapal.models.AdoptionForm
import com.judy.adoptapal.ui.theme.softTeal


@Composable
fun AdoptionForm(onSubmit: (AdoptionForm) -> Unit) {
    var fullName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var homeStatus by remember { mutableStateOf("") }
    var hasOwnedPet by remember { mutableStateOf(false) }
    var reason by remember { mutableStateOf("") }
    var petStayLocation by remember { mutableStateOf("") }
    var financiallyAble by remember { mutableStateOf(false) }
    var agreeReturn by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(softTeal)
        .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(value = fullName, onValueChange = { fullName = it }, label = { Text("Full Name") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = phoneNumber, onValueChange = { phoneNumber = it }, label = { Text("Phone Number") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone), modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = location, onValueChange = { location = it }, label = { Text("County or Estate") }, modifier = Modifier.fillMaxWidth())
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

        Button(
            onClick = {
                val form = AdoptionForm(
                    fullName, phoneNumber, location, homeStatus,
                    hasOwnedPet, reason, petStayLocation, financiallyAble, agreeReturn
                )
                onSubmit(form)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit Adoption Request")
        }
    }
}
@Composable
fun AdoptionForm_Screen(navController: NavHostController) {
    val context = LocalContext.current

    AdoptionForm(onSubmit = { form ->
        submitAdoptionForm(
            form = form,
            onSuccess = {
                Toast.makeText(context, "Form submitted successfully", Toast.LENGTH_SHORT).show()
                // navController.navigate("home")
            },
            onError = { e ->
                Toast.makeText(context, "Error: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
            }
        )
    })
}





@Preview
@Composable
private fun Adoptionprev() {
    AdoptionForm_Screen(rememberNavController())

}