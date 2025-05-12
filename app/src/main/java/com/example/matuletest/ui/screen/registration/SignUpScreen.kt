package com.example.matuletest.ui.screen.registration

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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.matuletest.R
import com.example.matuletest.ui.screen.registration.component.RegisterButton
import com.example.matuletest.ui.screen.registration.component.RegisterTextField
import com.example.matuletest.ui.screen.registration.component.TitleWithSubtitleText
import com.example.matuletest.ui.theme.MatuleTheme

@Composable
fun SignUpScreen(onNavigationToSigninScreen: () -> Boolean)
{
    val registrationViewModel: RegistrationViewModel = RegistrationViewModel()
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .padding(top = 35.dp)
                    .fillMaxWidth()
                    .height(40.dp)
            ) {
                IconButton(onClick = {onNavigationToSigninScreen()}) {
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
                    ) { onNavigationToSigninScreen() },
            ) {
                Text(
                    text = stringResource(R.string.reg_sign_in),
                    style = MatuleTheme.typography.bodyRegular16.copy(color = MatuleTheme.colors.text),
                    textAlign = TextAlign.Center
                )
            }
        }
    ) { paddingValues ->
        RegistrationContent(paddingValues, registrationViewModel)
    }
}


@Composable
fun RegistrationContent(paddingValues: PaddingValues, registrationViewModel: RegistrationViewModel){
    val registrationState = registrationViewModel.registrationState
    Column(modifier = Modifier.padding(paddingValues = paddingValues)) {
        TitleWithSubtitleText(
            title = stringResource(R.string.registration),
            subTitle = stringResource(R.string.sign_in_subtitile)
        )
        Spacer(modifier = Modifier.height(35.dp))

        RegisterTextField(
            value = registrationState.value.userName,
            onChangeValue = {
                registrationViewModel.setUserName(it)
            },
            isError = false,
            placeholder = {
                Text(text = stringResource(R.string.template_name))
            },
            supportingText = { Text(text = stringResource(R.string.Error_name)) },
            label = {
                Text(text = stringResource(R.string.name))
            }
        )

        RegisterTextField(
            value = registrationState.value.email,
            onChangeValue = {
                registrationViewModel.setEmail(it)
            },
            isError = registrationViewModel.emailHasError.value,
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
        RegisterTextField(
            value = registrationState.value.password,
            onChangeValue = {
                registrationViewModel.setPassword(it)
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

        SimpleCheckbox()

        RegisterButton(onClick = {}) {
            Text(text = stringResource(R.string.register))
        }
    }
}

@Composable
private fun SimpleCheckbox() {
    val isChecked = remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Checkbox(
            checked = isChecked.value,
            onCheckedChange = { isChecked.value = it }
        )
        Text(
            text = stringResource(R.string.Agreement_on_sharing_personal_info),
            style = MatuleTheme.typography.bodyRegular16.copy(color = MatuleTheme.colors.text),
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}