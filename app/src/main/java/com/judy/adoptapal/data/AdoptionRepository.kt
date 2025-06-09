package com.judy.adoptapal.data


import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.judy.adoptapal.models.AdoptionForm


fun submitAdoptionForm(
    form: AdoptionForm,
    onSuccess: () -> Unit,
    onError: (Exception) -> Unit
) {
    val db = Firebase.firestore
    db.collection("adoptionForms")
        .add(form)
        .addOnSuccessListener { onSuccess() }
        .addOnFailureListener { e -> onError(e) }
}





