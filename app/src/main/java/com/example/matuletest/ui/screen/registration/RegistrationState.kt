package com.example.matuletest.ui.screen.registration

data class RegistrationState (
    var userName: String = "",
    var email: String = "",
    var password: String = "",
    var isVisiblePassword: Boolean = false,
    var isLoading: Boolean = false,
    var isRegistration: Boolean = false,
    var errorMessage: String? = null
)