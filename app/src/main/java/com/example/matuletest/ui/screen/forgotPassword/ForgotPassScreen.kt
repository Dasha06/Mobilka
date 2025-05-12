package com.example.matuletest.ui.screen.forgotPassword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.matuletest.R
import com.example.matuletest.Screen
import com.example.matuletest.ui.screen.forgotPassword.component.ForgotPassButton
import com.example.matuletest.ui.screen.forgotPassword.component.ForgotPassTextField
import com.example.matuletest.ui.screen.forgotPassword.component.TitleWithSubtitleText
import com.example.matuletest.ui.theme.MatuleTheme
import kotlinx.coroutines.delay

@Composable
fun ForgotPassScreen(
    onNavigateToSignInScreen: () -> Boolean,
    navController: NavController
)
{
    val forgotPassViewModel: ForgotPassViewModel = ForgotPassViewModel()
    val showDialog = remember { mutableStateOf(false) }

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
            forgotPassViewModel,
            showDialog
            )
    }
    if (showDialog.value) {
        PopUpScreen(
            onDismiss = { showDialog.value = false },
        )
    }
}

@Composable
fun ForgotPassContent(
    paddingValues: PaddingValues,
    forgotPassViewModel: ForgotPassViewModel,
    showDialog: MutableState<Boolean>
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

        ForgotPassButton(onClick = {showDialog.value = true}) {
            Text(text = stringResource(R.string.forgot_pass_send))
        }
    }
}

@Composable
fun PopUpScreen(
    onDismiss: () -> Unit,
    ) {
    LaunchedEffect(Unit) {
        delay(10000L)
        onDismiss()
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {},
        title = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.email_image),
                        contentDescription = null,
                        tint = MatuleTheme.colors.accent,
                        modifier = Modifier.size(40.dp)
                    )
                }
                Text(
                    text = stringResource(R.string.pop_up_check_email),
                    style = MatuleTheme.typography.bodyRegular16.copy(color = MatuleTheme.colors.text),
                    textAlign = TextAlign.Center
                )
            }
        },
        text = {
            Text(
                text = stringResource(R.string.pop_up_message),
                textAlign = TextAlign.Center,
                style = MatuleTheme.typography.bodyRegular14.copy(color = MatuleTheme.colors.hint)
            )
        },
        modifier = Modifier.clip(RoundedCornerShape(14.dp)),
        containerColor = Color.White
    )
}
