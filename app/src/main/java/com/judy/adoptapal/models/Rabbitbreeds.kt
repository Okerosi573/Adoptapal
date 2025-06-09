package com.judy.adoptapal.models

data class RabbitBreed(val name: String, val imageUrl: String)

val rabbitBreeds = listOf(
    RabbitBreed("New Zealand White", "https://upload.wikimedia.org/wikipedia/commons/4/4b/New_Zealand_White_Rabbit_%28cropped%29.jpg"),
    RabbitBreed("Flemish Giant", "https://upload.wikimedia.org/wikipedia/commons/e/e4/Flemish_Giant_Rabbit.jpg"),
    RabbitBreed("Dutch Rabbit", "https://upload.wikimedia.org/wikipedia/commons/b/b7/Dutch_Rabbit_Black.jpg"),
    RabbitBreed("Mini Rex", "https://upload.wikimedia.org/wikipedia/commons/0/0e/Mini_Rex_Rabbit.jpg"),
    RabbitBreed("Lionhead", "https://upload.wikimedia.org/wikipedia/commons/e/ed/Lionhead_rabbit.jpg"),
    RabbitBreed("Holland Lop", "https://upload.wikimedia.org/wikipedia/commons/f/f3/Holland_Lop_Rabbit_%28cropped%29.jpg"),
    RabbitBreed("English Angora", "https://upload.wikimedia.org/wikipedia/commons/1/12/English_Angora_Rabbit.jpg"),
    RabbitBreed("Netherland Dwarf", "https://upload.wikimedia.org/wikipedia/commons/2/2e/Netherland_dwarf_rabbit.jpg")
)
