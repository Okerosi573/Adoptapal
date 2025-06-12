package com.judy.adoptapal.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.judy.adoptapal.models.AdoptionForm
import kotlinx.coroutines.launch

class AdoptionFormViewModel : ViewModel() {

    // Form fields
    var fullName by mutableStateOf("")
    var phoneNumber by mutableStateOf("")
    var location by mutableStateOf("")
    var homeStatus by mutableStateOf("")
    var hasOwnedPet by mutableStateOf(false)
    var reason by mutableStateOf("")
    var petStayLocation by mutableStateOf("")
    var financiallyAble by mutableStateOf(false)
    var agreeReturn by mutableStateOf(false)

    var isSubmitting by mutableStateOf(false)

    private val repository = AdoptionRepository()

    fun validate(): Boolean {
        return fullName.isNotBlank() &&
                phoneNumber.isNotBlank() &&
                location.isNotBlank() &&
                homeStatus.isNotBlank() &&
                reason.isNotBlank() &&
                petStayLocation.isNotBlank() &&
                agreeReturn
    }

    fun submit(
        userId: String,
        petId: String?,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        isSubmitting = true

        val form = AdoptionForm(
            fullName = fullName,
            phoneNumber = phoneNumber,
            location = location,
            homeStatus = homeStatus,
            hasOwnedPet = hasOwnedPet,
            reason = reason,
            petStayLocation = petStayLocation,
            financiallyAble = financiallyAble,
            agreeReturn = agreeReturn,
            userId = userId,
            petId = petId,
            timestamp = System.currentTimeMillis()
        )

        viewModelScope.launch {
            try {
                repository.submitForm(form)
                isSubmitting = false
                onSuccess()
            } catch (e: Exception) {
                isSubmitting = false
                onError(e)
            }
        }
    }
}


