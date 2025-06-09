package com.judy.adoptapal.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class AuthUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false
)

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _loginState = MutableStateFlow(AuthUiState())
    val loginState: StateFlow<AuthUiState> = _loginState

    private val _registerState = MutableStateFlow(AuthUiState())
    val registerState: StateFlow<AuthUiState> = _registerState

    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _loginState.value = AuthUiState(errorMessage = "Email and password must not be empty")
            return
        }

        _loginState.value = AuthUiState(isLoading = true)
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _loginState.value = AuthUiState(isSuccess = true)
                } else {
                    _loginState.value = AuthUiState(errorMessage = task.exception?.localizedMessage)
                }
            }
    }

    fun register(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        onComplete: (() -> Unit)? = null
    ) {
        if (email.isBlank() || password.isBlank() || firstName.isBlank() || lastName.isBlank()) {
            _registerState.value = AuthUiState(errorMessage = "All fields are required")
            return
        }

        _registerState.value = AuthUiState(isLoading = true)
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid
                    val user = hashMapOf(
                        "firstName" to firstName,
                        "lastName" to lastName,
                        "email" to email
                    )
                    userId?.let {
                        db.collection("users").document(it).set(user)
                            .addOnSuccessListener {
                                _registerState.value = AuthUiState(isSuccess = true)
                                onComplete?.invoke()
                            }
                            .addOnFailureListener { e ->
                                _registerState.value = AuthUiState(errorMessage = e.localizedMessage)
                            }
                    }
                } else {
                    _registerState.value = AuthUiState(errorMessage = task.exception?.localizedMessage)
                }
            }
    }

    fun clearLoginState() {
        _loginState.value = AuthUiState()
    }

    fun clearRegisterState() {
        _registerState.value = AuthUiState()
    }
}
