package com.example.matuletest.ui.screen.forgotPassword

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ForgotPassViewModel: ViewModel() {
    var forgotPassState = mutableStateOf(ForgotPassState())

    val emailHasError = derivedStateOf {
        if (forgotPassState.value.email.isEmpty()) return@derivedStateOf false
        !android.util.Patterns.EMAIL_ADDRESS.matcher(forgotPassState.value.email).matches()
    }

    fun setEmail(email: String){
        forgotPassState.value = forgotPassState.value.copy(email = email)
    }
}