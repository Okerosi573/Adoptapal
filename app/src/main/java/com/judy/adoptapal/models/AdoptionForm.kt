package com.judy.adoptapal.models


data class AdoptionForm(
    val fullName: String,
    val phoneNumber: String,
    val location: String,
    val homeStatus: String,
    val hasOwnedPet: Boolean,
    val reason: String,
    val petStayLocation: String,
    val financiallyAble: Boolean,
    val agreeReturn: Boolean,
    val userId: String,
    val petId: String? = null,
    val timestamp: Long
)
