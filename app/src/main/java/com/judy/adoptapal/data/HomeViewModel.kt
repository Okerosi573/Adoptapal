package com.judy.adoptapal.data

import androidx.lifecycle.ViewModel
import com.judy.adoptapal.R
import com.judy.adoptapal.models.Pet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {

    private val _pets = MutableStateFlow<List<Pet>>(emptyList())
    val pets: StateFlow<List<Pet>> = _pets

    init {
        loadPets()
    }

    private fun loadPets() {
        _pets.value = listOf(
            Pet("dog1", "Labrador", R.drawable.ctdog, "Dog","2years","10kgs"),
            Pet("dog2", "Poodle", R.drawable.boxpuppy, "Dog","2years","10kgs"),
            Pet("cat1", "Siamese", R.drawable.ctcat, "Cat","2years","10kgs"),
            Pet("cat2", "Persian", R.drawable.ctcat, "Cat","2years","10kgs"),
            Pet("rabbit1", "White Rabbit", R.drawable.ctrabbit, "Rabbit","2years","10kgs"),
            Pet("rabbit2", "Brown Bunny", R.drawable.ctrabbit, "Rabbit","2years","10kgs"),
        )
    }
}
