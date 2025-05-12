package com.example.matuletest.ui.screen.OTRVerification

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.matuletest.R
import com.example.matuletest.ui.screen.OTRVerification.component.TitleWithSubtitleText
import com.example.matuletest.ui.theme.MatuleTheme
import kotlinx.coroutines.delay

@Composable
fun OTRVerificationScreen(
    navController: NavController,
    email: String = "user@example.com"
) {
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
        }){paddingValues -> OTRVerificationContent(paddingValues)}
}

@Composable
fun OTRVerificationContent(paddingValues: PaddingValues) {
    var timer by remember { mutableStateOf(30) }
    val otpFields = remember { Array(5) { mutableStateOf("") } }

    LaunchedEffect(timer) {
        while (timer > 0) {
            delay(1000)
            timer--
        }
    }
    Column(
        modifier = Modifier
            .padding(paddingValues = paddingValues)
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TitleWithSubtitleText(
            title = "ОТР Проверка",
            subTitle = "Пожалуйста, проверьте свою электронную почту, чтобы увидеть код подтверждения"
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "ОТР Код",
            style = MatuleTheme.typography.subTitleRegular16.copy(
                color = MatuleTheme.colors.text,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
                .padding(bottom = 16.dp),
            textAlign = TextAlign.Start
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(5) { index ->
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            color = MatuleTheme.colors.background,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    BasicTextField(
                        value = otpFields[index].value,
                        onValueChange = { value ->
                            if (value.length <= 1) otpFields[index].value = value
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        textStyle = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = MatuleTheme.colors.text,
                            textAlign = TextAlign.Center
                        ),
                        decorationBox = { innerTextField ->
                            Box(contentAlignment = Alignment.Center) {
                                innerTextField()
                            }
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Отправить заново",
                style = MatuleTheme.typography.bodyRegular14.copy(
                    color = MatuleTheme.colors.hint
                ),
                modifier = Modifier.clickable(enabled = timer <= 0) {
                    if (timer <= 0) timer = 30
                }
            )

            Text(
                text = String.format("%02d:%02d", timer / 60, timer % 60),
                style = MatuleTheme.typography.bodyRegular14.copy(
                    color = MatuleTheme.colors.hint
                )
            )
        }
    }
}

@Composable
fun StartTimer(timer:Int)
{
    var timer = timer
    LaunchedEffect(Unit) {
        while (timer > 0) {
            delay(1000)
            timer--
        }
        timer = 30
    }
}