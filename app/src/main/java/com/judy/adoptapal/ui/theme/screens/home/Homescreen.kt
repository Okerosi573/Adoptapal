@file:OptIn(ExperimentalMaterial3Api::class)


package com.judy.adoptapal.ui.theme.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.judy.adoptapal.navigation.ROUTE_CATLIST
import com.judy.adoptapal.navigation.ROUTE_DOGLIST
import com.judy.adoptapal.navigation.ROUTE_RABBITLIST
import com.judy.adoptapal.ui.theme.ButtonBackground
import com.judy.adoptapal.ui.theme.babyBlue
import com.judy.adoptapal.ui.theme.bluu
import com.judy.adoptapal.ui.theme.button
import com.judy.adoptapal.ui.theme.clearSky
import com.judy.adoptapal.ui.theme.lavender
import com.judy.adoptapal.ui.theme.paleAqua
import com.judy.adoptapal.ui.theme.paleDenim
import com.judy.adoptapal.ui.theme.paleLavender
import com.judy.adoptapal.ui.theme.tealBlue

val PrimaryBlue = Color(0xFF1E88E5)
val LightBlue = Color(0xFFBBDEFB)
val SoftPink = Color(0xFFFCE4EC)
val NeutralBackground = Color(0xFFF5F5F5)
val ButtonBlue = Color(0xFF1976D2)
val CardBackground = Color(0xFFE3F2FD)

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
                title = {
                    Text(
                        "Adoptapal",
                        fontFamily = FontFamily.Serif,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                actions = {
                    IconButton(onClick = { navController.navigate("UserProfile") }) {
                        Icon(imageVector = Icons.Default.Person, contentDescription = "User Profile", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = bluu)
            )
        },
        containerColor = NeutralBackground
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(NeutralBackground)
                .padding(WindowInsets.systemBars.only(WindowInsetsSides.Top + WindowInsetsSides.Horizontal).asPaddingValues())
                .padding(padding)
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .verticalScroll(rememberScrollState()),
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
            .padding(horizontal = 12.dp)
            .height(180.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .align(Alignment.BottomCenter),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = LightBlue)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        "Welcome${userName?.let { ", $it" } ?: ""}!\nEnjoy finding your furry friend!",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        color = Color.Black
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource(R.drawable.boxpuppy),
                    contentDescription = "Puppy",
                    modifier = Modifier
                        .size(110.dp)
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
            .padding(horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Choose your pet",
                fontWeight = FontWeight.SemiBold,
                color = bluu,
                fontSize = 16.sp,
                fontFamily = FontFamily.Serif
            )
            Spacer(modifier = Modifier.height(8.dp))
            PetCategoryRow(navController)
        }
    }
}

@Composable
fun PetCategoryRow(navController: NavHostController) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        PetCategoryCard("Dog", R.drawable.ctdog, lavender, Modifier.weight(1f)) {
            navController.navigate("PetSection/Dog")
        }
        PetCategoryCard("Cat", R.drawable.ctcat, SoftPink, Modifier.weight(1f)) {
            navController.navigate("PetSection/Cat")
        }
        PetCategoryCard("Rabbit", R.drawable.ctrabbit, NeutralBackground, Modifier.weight(1f)) {
            navController.navigate("PetSection/Rabbit")
        }
    }
}

@Composable
fun PetCategoryCard(
    label: String,
    iconRes: Int,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .aspectRatio(0.7f)
            .clip(RoundedCornerShape(16.dp))
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
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = label,
                modifier = Modifier.size(65.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
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
            .padding(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(button)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Breed Gallery",
                color = bluu,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                fontFamily = FontFamily.Serif
            )
            listOf("Dogs" to (ROUTE_DOGLIST), "Cats" to (ROUTE_CATLIST), "Rabbit" to (ROUTE_RABBITLIST)).forEach { (label, route) ->
                Button(
                    onClick = { navController.navigate(route) },
                    colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue),
                    modifier = Modifier
                        .fillMaxWidth(0.65f)
                        .height(48.dp)
                ) {
                    Text(label, color = Color.White)
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreenPrev() {
    val navController = rememberNavController()
    Home_Screen(navController)
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomePrev() {
    val navController = rememberNavController()
    Home_Screen(navController)
}
