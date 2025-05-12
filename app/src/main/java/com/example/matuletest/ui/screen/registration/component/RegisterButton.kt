package com.example.matuletest.ui.screen.registration.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.matuletest.common.CommonButton
import com.example.matuletest.ui.theme.MatuleTheme

@Composable
fun RegisterButton(

    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
){
    CommonButton(onClick = onClick,
        buttonColors = ButtonColors(
            contentColor = MatuleTheme.colors.background,
            containerColor = MatuleTheme.colors.accent,
            disabledContentColor = MatuleTheme.colors.accent,
            disabledContainerColor = MatuleTheme.colors.accent
        ),
        modifier = modifier.padding(top = 10.dp),
    ){
        content()
    }
}