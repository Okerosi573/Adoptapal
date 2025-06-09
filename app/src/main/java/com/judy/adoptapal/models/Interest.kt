package com.judy.adoptapal.models

data class Interest(
    val petId: String = "",
    val petName: String = "",
    val userId: String = "",
    val userName: String = "",
    val timestamp: Long = System.currentTimeMillis()
)
