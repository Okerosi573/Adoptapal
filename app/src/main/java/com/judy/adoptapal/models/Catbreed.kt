package com.judy.adoptapal.models

data class CatBreed(val name: String, val imageUrl: String)

val catBreedsInKenya = listOf(
    CatBreed("Domestic Shorthair", "https://upload.wikimedia.org/wikipedia/commons/6/6e/Domestic_shorthair_cat.jpg"),
    CatBreed("Persian", "https://images.unsplash.com/photo-1592194996308-7b43878e84a6"),
    CatBreed("Maine Coon", "https://images.unsplash.com/photo-1619982520468-87a1f17c5b48"),
    CatBreed("Siamese", "https://images.unsplash.com/photo-1592194996306-7a4ff02c2db2"),
    CatBreed("Abyssinian", "https://upload.wikimedia.org/wikipedia/commons/e/e6/Abyssinian_cat_-_ruddy.jpg"),
    CatBreed("Bengal", "https://images.unsplash.com/photo-1583337130417-3346a1be7dee"),
    CatBreed("Ragdoll", "https://images.unsplash.com/photo-1619983081534-b3ff446dcb90"),
    CatBreed("British Shorthair", "https://images.unsplash.com/photo-1592194996310-116f7793c7f5"),
    CatBreed("Russian Blue", "https://upload.wikimedia.org/wikipedia/commons/6/6b/Russian_Blue_-_Lili.jpg"),
    CatBreed("Sphynx", "https://upload.wikimedia.org/wikipedia/commons/2/25/Sphynx_cat_by_shira.jpg")
)
