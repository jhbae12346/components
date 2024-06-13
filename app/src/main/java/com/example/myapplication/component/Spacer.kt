package com.example.myapplication.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.HmmTheme
import com.example.myapplication.ui.theme.SmallGray

@Composable
@NonRestartableComposable
fun HmSpacer(
    modifier: Modifier = Modifier
) {
    Spacer(
        modifier = modifier
            .fillMaxWidth()
            .height(3.dp)
            .background(SmallGray)
    )
}

@Preview
@Composable
private fun HmSpacerPreview() {
    HmmTheme {
        HmSpacer()
    }
}