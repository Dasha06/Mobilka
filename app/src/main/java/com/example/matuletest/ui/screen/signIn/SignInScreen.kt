package com.example.matuletest.ui.screen.signIn

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
//import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.matuletest.ui.screen.signIn.component.AuthButton
import com.example.matuletest.ui.screen.signIn.component.AuthTextField
import com.example.matuletest.ui.screen.signIn.component.TitleWithSubtitleText
import com.example.matuletest.ui.theme.MatuleTheme
import com.example.matuletest.R


@Composable
fun SignInScreen(
    onNavigationToRegScreen: () -> Unit,
    navController: NavController) {
    val signInViewModel: SignInViewModel = SignInViewModel()
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .padding(top = 35.dp)
                    .fillMaxWidth()
                    .height(40.dp)
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(R.drawable.back_arrow),
                        contentDescription = null)
                }
            }
        },
        bottomBar = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 50.dp)
                    .fillMaxWidth()
                    .height(40.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) { onNavigationToRegScreen() },
            ) {
                Text(
                    text = stringResource(R.string.sign_up),
                    style = MatuleTheme.typography.bodyRegular16.copy(color = MatuleTheme.colors.text),
                    textAlign = TextAlign.Center
                )
            }
        }
    ) { paddingValues ->
        SignInContent(paddingValues,
            signInViewModel,
            navController = navController)
    }
}
@Composable
fun SignInContent(
    paddingValues: PaddingValues,
    signInViewModel: SignInViewModel,
    navController: NavController){
    val signInState = signInViewModel.signInState
    Column(
        modifier = Modifier.padding(paddingValues = paddingValues),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TitleWithSubtitleText(
            title = stringResource(R.string.hello),
            subTitle = stringResource(R.string.sign_in_subtitile)
        )
        Spacer(modifier = Modifier.height(35.dp))

        AuthTextField(
            value = signInState.value.email,
            onChangeValue = {
                signInViewModel.setEmail(it)
            },
            isError = signInViewModel.emailHasError.value,
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
        AuthTextField(
            value = signInState.value.password,
            onChangeValue = {
                signInViewModel.setPassword(it)
            },
            isError = false,
            placeholder = {
                Text(text = stringResource(R.string.password_template))
            },
            supportingText = {
                Text(text = stringResource(R.string.uncorrtect_password))
            },
            label = {
                Text(text = stringResource(R.string.password))
            }
        )

        Text(
            text = "Восстановить",
            modifier = Modifier
                .clickable(onClick = {navController.navigate("forgotpass")})
                .padding(top = 8.dp, end = 25.dp)
                .fillMaxWidth(),
            color = MatuleTheme.colors.subTextDark.copy(),
            style = MatuleTheme.typography.bodyRegular12.copy(),
            textAlign = TextAlign.End
        )

        AuthButton(
            onClick = {}
        ) {
            Text(stringResource(R.string.sign_in))
        }
    }
}





