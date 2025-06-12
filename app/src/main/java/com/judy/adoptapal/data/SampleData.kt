package com.judy.adoptapal.data



import com.judy.adoptapal.R
import com.judy.adoptapal.models.Pet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

val samplePets = listOf(
    Pet(
        id = "1",
        name = "Buddy",
        category = "Dog",
        breed = "German Shepherd",
        gender = "Male",
        age = "2 years",
        weight = "20 kg",
        imageRes = R.drawable.dog // replace with actual drawable
    ),
    Pet(
        id = "2",
        name = "Whiskers",
        category = "Cat",
        breed = "Persian",
        gender = "Female",
        age = "1 year",
        weight = "5 kg",
        imageRes = R.drawable.ctcat // replace with actual drawable
    )
)
