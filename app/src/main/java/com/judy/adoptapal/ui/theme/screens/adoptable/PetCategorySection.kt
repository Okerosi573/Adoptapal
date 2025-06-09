

package com.judy.adoptapal.ui.theme.screens.adoptable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.judy.adoptapal.data.HomeViewModel
import com.judy.adoptapal.models.Pet
import com.judy.adoptapal.ui.theme.bluu
import com.judy.adoptapal.ui.theme.screens.adoptable.PetListItem

@Composable
fun PetCategoryTabs(
    navController: NavHostController,
    selectedCategory: String,
    homeViewModel: HomeViewModel = viewModel()

) {
    val pets by homeViewModel.pets.collectAsState()


    val categories = listOf("Dog", "Cat", "Rabbit")

    var selectedTab by remember {
        mutableStateOf(categories.indexOf(selectedCategory).coerceAtLeast(0))
    }

    Column {
        TabRow(selectedTabIndex = selectedTab) {
            categories.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = { Text(title) }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        val filteredPets = pets.filter { it.category == categories[selectedTab] }

        LazyColumn(
            modifier = Modifier.fillMaxHeight(0.5f), // adjust height as needed
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(filteredPets) { pet ->
                PetListItem(pet = pet, onInterestedClick = {
                    navController.navigate("adoption_form/${pet.id}")
                })
            }
        }
    }
}

@Composable
fun PetListItem(pet: Pet, onInterestedClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(pet.imageRes),
                contentDescription = pet.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(10.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(pet.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(pet.category, fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(4.dp))

                Text("Age: ${pet.age}", fontSize = 14.sp, color = Color.DarkGray)
                Text("Weight: ${pet.weight}", fontSize = 14.sp, color = Color.DarkGray)
            }

            Button(
                onClick = onInterestedClick,
                colors = ButtonDefaults.buttonColors(containerColor = bluu)
            ) {
                Text("Interested", color = Color.White)
            }
        }
    }
}
@Composable
fun PetSectionScreen(navController: NavHostController, petType: String) {
    // Pass the type to your tabs
    PetCategoryTabs(navController = navController, selectedCategory = petType)
}


@Preview
@Composable
private fun PetScatprev() {
    PetCategoryTabs(
        rememberNavController(),
        selectedCategory = TODO(),
        homeViewModel = TODO()
    )

}
