package com.example.matuletest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.matuletest.ui.screen.OTRVerification.OTRVerificationScreen
import com.example.matuletest.ui.screen.forgotPassword.ForgotPassScreen
import com.example.matuletest.ui.screen.registration.SignUpScreen
import com.example.matuletest.ui.screen.signIn.SignInScreen
import com.example.matuletest.ui.theme.MatuleTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MatuleTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screen.SignIn.route
                ) {
                    composable(Screen.SignIn.route) {
                        SignInScreen(
                            onNavigationToRegScreen = {
                                navController.navigate(Screen.Registration.route)
                            },
                            navController = navController
                        )
                    }

                    composable(Screen.Registration.route) {
                        SignUpScreen(
                            onNavigationToSigninScreen = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable(Screen.ForgotPass.route){
                        ForgotPassScreen(
                            onNavigateToSignInScreen = { navController.popBackStack() },
                            navController = navController
                        )
                    }

                    composable(Screen.Verification.route) {
                        OTRVerificationScreen(navController = navController)
                    }
                }
            }
        }
    }
}

