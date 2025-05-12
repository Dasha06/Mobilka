package com.example.matuletest.ui.screen.forgotPassword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.matuletest.R
import com.example.matuletest.ui.screen.forgotPassword.component.ForgotPassButton
import com.example.matuletest.ui.screen.forgotPassword.component.ForgotPassTextField
import com.example.matuletest.ui.screen.forgotPassword.component.TitleWithSubtitleText

@Composable
fun ForgotPassScreen(
    onNavigateToSignInScreen: () -> Boolean,
    navController: NavController
)
{
    val forgotPassViewModel: ForgotPassViewModel = ForgotPassViewModel()
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .padding(top = 35.dp)
                    .fillMaxWidth()
                    .height(40.dp)
            ) {
                IconButton(onClick = {onNavigateToSignInScreen()}) {
                    Icon(
                        painter = painterResource(R.drawable.back_arrow),
                        contentDescription = null)
                }
            }
        }
    ){paddingValues ->
        ForgotPassContent(
            paddingValues,
            forgotPassViewModel
            )

    }
}

@Composable
fun ForgotPassContent(
    paddingValues: PaddingValues,
    forgotPassViewModel: ForgotPassViewModel
    ) {
    val forgotPassState = forgotPassViewModel.forgotPassState
    Column(
        modifier = Modifier.padding(paddingValues = paddingValues),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TitleWithSubtitleText(
            title = stringResource(R.string.ForgotPassword),
            subTitle = stringResource(R.string.Forgot_pass_subtitle)
        )
        ForgotPassTextField(
            value = forgotPassState.value.email,
            onChangeValue = {
                forgotPassViewModel.setEmail(it)
            },
            isError = forgotPassViewModel.emailHasError.value,
            placeholder = {
                Text(text = stringResource(R.string.template_email))
            },
            supportingText = {
                Text(text = stringResource(R.string.uncorrect_email))
            },
            label = {
                Text(text = stringResource(R.string.email))
            }
        )

        ForgotPassButton(onClick = {}) {
            Text(text = stringResource(R.string.forgot_pass_send))
        }
    }
}