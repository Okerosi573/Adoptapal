package com.judy.adoptapal.ui.theme.screens.adoptable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
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
import androidx.compose.material3.Scaffold
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
import com.judy.adoptapal.navigation.ROUTE_ADOPTIONFORM
import com.judy.adoptapal.ui.theme.bluu
import com.judy.adoptapal.ui.theme.ivoryGold


@Composable
fun PetCategoryTabs(
    onInterestedClick: (String) -> Unit,
    selectedCategory: String,
    homeViewModel: HomeViewModel = viewModel()
) {
    val pets by homeViewModel.pets.collectAsState()
    val categories = listOf("Dog", "Cat", "Rabbit")

    var selectedTab by remember {
        mutableStateOf(categories.indexOf(selectedCategory).coerceAtLeast(0))
    }

    val filteredPets = pets.filter { it.category == categories[selectedTab] }

    Scaffold(
        topBar = {
        }
    ) { paddingValues ->
        Column(modifier = Modifier
            .padding(WindowInsets.systemBars.only(WindowInsetsSides.Top + WindowInsetsSides.Horizontal).asPaddingValues())
            .padding(paddingValues)) {
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

            LazyColumn(
                modifier = Modifier.fillMaxHeight(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredPets) { pet ->
                    PetListItem(pet = pet, onInterestedClick = {
                        onInterestedClick(pet.id)
                    })
                }

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
            verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = painterResource(pet.imageRes),
                contentDescription = pet.name,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(10.dp))
            )

            Spacer(modifier = Modifier.width(20.dp))

            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(pet.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text("🐾 Breed: ${pet.breed}", fontSize = 14.sp, color = (ivoryGold))
                Text("📂 Category: ${pet.category}", fontSize = 14.sp, color = (ivoryGold))
                Text("⚖️ Weight: ${pet.weight}", fontSize = 14.sp, color = (ivoryGold))
                Text("♀️♂️ Gender: ${pet.gender}", fontSize = 14.sp, color = (ivoryGold))
                Text("🎂 Age: ${pet.age}", fontSize = 14.sp, color = (ivoryGold))

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = onInterestedClick,
                    colors = ButtonDefaults.buttonColors(containerColor = bluu),
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Interested", color = Color.White)
                }
            }
        }
    }
}



@Composable
fun PetSectionScreen(navController: NavHostController, petType: String) {
    PetCategoryTabs(
        selectedCategory = petType,
        onInterestedClick = { petId ->
            navController.navigate("adoption_form/$petId")
        }
    )
}


@Preview
@Composable
fun PetCategoryTabsPreview() {
    PetCategoryTabs(selectedCategory = "Dog", onInterestedClick = {})
}

