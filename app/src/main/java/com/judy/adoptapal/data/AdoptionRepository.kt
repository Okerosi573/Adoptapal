package com.judy.adoptapal.data

import com.google.firebase.firestore.FirebaseFirestore
import com.judy.adoptapal.models.AdoptionForm
import kotlinx.coroutines.tasks.await

class AdoptionRepository {
    private val db = FirebaseFirestore.getInstance()

    suspend fun submitForm(form: AdoptionForm) {
        db.collection("adoptionForms")
            .add(form)
            .await() // suspends until complete
    }
}


