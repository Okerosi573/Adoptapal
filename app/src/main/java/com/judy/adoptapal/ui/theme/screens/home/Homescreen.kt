@file:OptIn(ExperimentalMaterial3Api::class)


package com.judy.adoptapal.ui.theme.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.judy.adoptapal.R
import com.judy.adoptapal.navigation.ROUTE_PETCATEGORYSECTION
import com.judy.adoptapal.navigation.ROUTE_USERPROFILE
import com.judy.adoptapal.ui.theme.bluu
import com.judy.adoptapal.ui.theme.button
import com.judy.adoptapal.ui.theme.clearSky
import com.judy.adoptapal.ui.theme.cobaltMist
import com.judy.adoptapal.ui.theme.eveningLilac
import com.judy.adoptapal.ui.theme.horizon
import com.judy.adoptapal.ui.theme.lilacSmoke
import com.judy.adoptapal.ui.theme.softLilac
import com.judy.adoptapal.ui.theme.softTeal


@Composable
fun Home_Screen(navController: NavHostController) {
    var userName by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        val auth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()
        val userId = auth.currentUser?.uid
        if (userId != null) {
            db.collection("users").document(userId).get()
                .addOnSuccessListener { document ->
                    userName = document.getString("firstName")
                }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Adoptapal",
                    fontFamily = FontFamily.Serif,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = (bluu))},
                actions = {
                    IconButton(onClick = {
                        navController.navigate(ROUTE_USERPROFILE)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "User Profile"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    actionIconContentColor = Color.White
                )
            )
        },
        containerColor = cobaltMist
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(horizon)
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
                     // space for bottom card
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TopAdoptionCard(userName)
                Spacer(modifier = Modifier.height(12.dp))
                PetCategoryCardSection(navController)
            }

            BottomPetButtons(navController)
        }
    }
}

@Composable
fun TopAdoptionCard(userName: String?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(195.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .align(Alignment.BottomCenter),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Row(
                modifier = Modifier
                    .background(button)
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .background(button)
                        .weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        "Welcome${userName?.let { ", $it" } ?: ""}!\nPaw-sitively in love with you!",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                }

                Spacer(modifier = Modifier.width(8.dp))

                Image(
                    painter = painterResource(R.drawable.boxpuppy),
                    contentDescription = "Puppy",
                    modifier = Modifier
                        .size(130.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
            }
        }
    }
}

@Composable
fun PetCategoryCardSection(navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(eveningLilac)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Choose your pet",
                fontWeight = FontWeight.SemiBold,
                color = (bluu),
                fontSize = 16.sp,
                fontFamily = FontFamily.Serif,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp)
            )

            Spacer(modifier = Modifier.height(1.dp))

            PetCategoryRow(navController)
        }
    }
}

@Composable
fun PetCategoryRow(navController: NavHostController) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp)
    ) {
        PetCategoryCard("Dog", R.drawable.ctdog, Color(0xFFFFF3E0)) {
            navController.navigate("PetSection/Dog")
        }
        PetCategoryCard("Cat", R.drawable.ctcat, Color(0xFFFFEBEE)) {
            navController.navigate("PetSection/Cat")
        }
        PetCategoryCard("Rabbit", R.drawable.ctrabbit, Color(0xFFECEFF1)) {
            navController.navigate("PetSection/Rabbit")
        }
    }
}

@Composable
fun PetCategoryCard(
    label: String,
    iconRes: Int,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(75.dp)
            .height(130.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor)
            .clickable(
                onClick = onClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .padding(top = 0.1.dp)
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = label,
                modifier = Modifier.size(85.dp)
            )
            Spacer(modifier = Modifier.height(0.3.dp))
            Text(
                text = label,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily.Cursive,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        }
    }
}

@Composable
fun BottomPetButtons(navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(11.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(button)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Breed Gallery",
                color = (bluu),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                fontFamily = FontFamily.Serif
            )
            Button(
                onClick = { navController.navigate("Doglist_Screen") },
                colors = ButtonDefaults.buttonColors(bluu),
                modifier = Modifier
                    .width(130.dp)
                    .height(45.dp)
            ) {
                Text("Dogs",
                    color = Color.White)
            }

            Button(
                onClick = { navController.navigate("Catlist_Screen") },
                colors = ButtonDefaults.buttonColors(bluu),
                modifier = Modifier
                    .width(130.dp)
                    .height(45.dp)
            ) {
                Text("Cats",
                    color = Color.White)
            }

            Button(
                onClick = { navController.navigate("Rabbitlist_Screen") },
                colors = ButtonDefaults.buttonColors(bluu),
                modifier = Modifier
                    .width(130.dp)
                    .height(45.dp)
            ) {
                Text("Rabbit",
                    color = Color.White)
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomePrev() {
    val navController = rememberNavController()
    Home_Screen(navController)
}

