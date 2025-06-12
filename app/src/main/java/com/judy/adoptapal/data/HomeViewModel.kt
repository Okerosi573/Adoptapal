package com.judy.adoptapal.data

import androidx.lifecycle.ViewModel
import com.judy.adoptapal.R
import com.judy.adoptapal.models.Pet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface PetProvider {
    val pets: StateFlow<List<Pet>>
}

class HomeViewModel : ViewModel() {


    internal val _pets = MutableStateFlow<List<Pet>>(emptyList())
    val pets: StateFlow<List<Pet>> = _pets

    init {
        loadPets()
    }

    private fun loadPets() {
        _pets.value = listOf(
            //dogs
            Pet("dog1", "Binx", R.drawable.pug, "Dog","5months","7kgs","Pug","Male"),
            Pet("dog2", "Simba", R.drawable.rottweiler, "Dog","2years","20kgs","Rottweiler","Male"),
            Pet("dog3", "Rex", R.drawable.german, "Dog","2years","14kgs","German Shepherd","Female"),
            Pet("dog4", "Coco", R.drawable.maltese, "Dog","6months","7kgs","Maltese","Female"),
            Pet("dog5", "Kona", R.drawable.samoyed, "Dog","4months","14kgs","Samoyed","Male"),
            Pet("dog6", "Thumper", R.drawable.dachshund, "Dog","3months","8kgs","Dachshund","Female"),
            Pet("dog7", "Rocky", R.drawable.greatdane, "Dog","3years","21kgs","Great Dane","Male"),

            //cats
            Pet("cat1", "Pip", R.drawable.sphynx, "Cat","1year","8kgs","Sphyx","Female"),
            Pet("cat2", "Mochi", R.drawable.russianblu, "Cat","4months","6kgs","Russian Blue","Male"),
            Pet("cat3", "Luna", R.drawable.mau, "Cat","11months","6kgs","Mau","Feamle"),
            Pet("cat4", "Milo", R.drawable.persian, "Cat","9months","8kgs","Persian","Female"),
            Pet("cat5", "Peanut", R.drawable.siamese, "Cat","6months","6kgs","Siamese","Female"),
            Pet("cat6", "Toffee", R.drawable.bengal, "Cat","8months","5kgs","Bengal","Male"),
            Pet("cat7", "Puss", R.drawable.ragdoll, "Cat","5months","8kgs","Ragdoll","Female"),
            //rabbits
            Pet("rabbit1", "Misty", R.drawable.dutch, "Rabbit","3months","4kgs","Dutch Rabbit","Female"),
            Pet("rabbit2", "Maple", R.drawable.minirex, "Rabbit","2months","3kgs","Mini Rex","Male"),
            Pet("rabbit3", "Clover", R.drawable.flemishgiant, "Rabbit","1year","13kgs","Flemish Giant","Male"),
            Pet("rabbit4", "Pumpkin", R.drawable.lionhead, "Rabbit","5months","6kgs","Lion Head","Female"),
            Pet("rabbit5", "Binky", R.drawable.englishangora, "Rabbit","8months","8kgs","English Angora","Male"),
            Pet("rabbit6", "Snowball", R.drawable.newzealandwhite, "Rabbit","11months","7kgs","NewZealand White","Female"),
        )
    }
}
