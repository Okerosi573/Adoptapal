package com.judy.adoptapal.models



data class AdoptionForm(
    val fullName: String = "",
    val phoneNumber: String = "",
    val location: String = "",
    val homeStatus: String = "",
    val hasOwnedPet: Boolean = false,
    val reason: String = "",
    val petStayLocation: String = "",
    val financiallyAble: Boolean = false,
    val agreeReturn: Boolean = false,
    val timestamp: Long = System.currentTimeMillis()
)

