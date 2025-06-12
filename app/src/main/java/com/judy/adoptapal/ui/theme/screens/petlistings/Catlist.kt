package com.judy.adoptapal.ui.theme.screens.petlistings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.judy.adoptapal.models.CatBreed
import com.judy.adoptapal.models.catBreedsInKenya
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.LocalImageLoader
import com.judy.adoptapal.ui.theme.ivoryGold
import okhttp3.OkHttpClient


// Safe placeholder data
val catBreedsInKenya = listOf(
        CatBreed("African Shorthair", "https://images.unsplash.com/photo-1731985967430-1b91cca5a936?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
        CatBreed("Savannah Cat", "https://images.unsplash.com/photo-1594084442492-890cdf85dbcf?q=80&w=2080&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
        CatBreed("Mau", "https://media.istockphoto.com/id/859638828/photo/breed-representative-egyptian-mau-cat.jpg?s=1024x1024&w=is&k=20&c=ZCDPcWv2Dtm-VKdqrw2zKWaP4NAvXShUAYVygeNu9C8="),
        CatBreed("Domestic Shorthair", "https://images.unsplash.com/photo-1661803859824-79f8c03c6c62?q=80&w=1972&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
        CatBreed("Persian", "https://images.unsplash.com/photo-1585137173132-cf49e10ad27d?q=80&w=1972&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
        CatBreed("Maine Coon", "https://images.unsplash.com/photo-1614381016538-5f80a0852fea?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
        CatBreed("Siamese", "https://images.unsplash.com/photo-1568152950566-c1bf43f4ab28?q=80&w=1965&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
        CatBreed("Abyssinian", "https://images.unsplash.com/photo-1665952196039-8b0ca388d942?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
        CatBreed("Bengal", "https://images.unsplash.com/photo-1603277160434-df7471138363?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
        CatBreed("Ragdoll", "https://images.unsplash.com/photo-1620933288385-b2f6f1931d9e?q=80&w=2072&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
        CatBreed("British Shorthair", "https://images.unsplash.com/photo-1584396888493-06386077e877?q=80&w=1973&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
        CatBreed("Russian Blue", "https://images.unsplash.com/photo-1734306504997-39ed2997eb87?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
        CatBreed("Sphynx", "https://images.unsplash.com/photo-1626881255860-1e44d6b611ad?q=80&w=1970&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
)

@Composable
fun Catlist_Screen(navController: NavController) {
    val context = LocalContext.current

    // Custom Coil ImageLoader that handles redirects
    val imageLoader = remember {
        ImageLoader.Builder(context)
            .okHttpClient {
                OkHttpClient.Builder()
                    .followRedirects(true)
                    .followSslRedirects(true)
                    .build()
            }
            .build()
    }

    // Provide the image loader to all AsyncImage composables
    CompositionLocalProvider(LocalImageLoader provides imageLoader) {
        var searchQuery by remember { mutableStateOf("") }

        val filteredCats = catBreedsInKenya.filter {
            it.name.contains(searchQuery, ignoreCase = true)
        }

        Column(modifier = Modifier.fillMaxSize()) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search Cat Breeds") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(WindowInsets.systemBars.only(WindowInsetsSides.Top + WindowInsetsSides.Horizontal).asPaddingValues())
                    .padding(horizontal = 16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.Gray
                )

            )

            if (filteredCats.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No results found")
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(filteredCats) { cat ->
                        CatCard(cat)
                    }
                }
            }
        }
    }
}

@Composable
fun CatCard(breed: CatBreed) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = breed.imageUrl.ifEmpty { "https://via.placeholder.com/100" },
                contentDescription = breed.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = breed.name,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CatlistPreview() {
    val navController = rememberNavController()
    Catlist_Screen(navController)
}
