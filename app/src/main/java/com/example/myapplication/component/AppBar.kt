package com.example.myapplication.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.DarkBlue
import com.example.myapplication.ui.theme.HmmTheme
import com.example.myapplication.ui.theme.KTCTheme
import com.example.myapplication.ui.theme.Red03
import com.example.myapplication.ui.theme.White

@Composable
fun HmAppBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationType: TopAppBarType = TopAppBarType.Default,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    onBackClick: () -> Unit = {},
) {
    CompositionLocalProvider(LocalContentColor provides contentColor) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .background(DarkBlue)
            .pointerInput(Unit) { /* no-op */ }
            .then(modifier)
            .padding(vertical = 8.5.dp, horizontal = 16.dp)) {
            Box(modifier = Modifier.align(Alignment.CenterEnd)) {
                when (navigationType) {
                    TopAppBarType.Default -> {}
                    is TopAppBarType.Custom -> navigationType.content()
                    is TopAppBarType.Button -> TopButton(
                        text = navigationType.text, onClick = navigationType.onClick
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = null,
                    tint = White,
                    modifier = Modifier
                        .size(34.dp)
                        .clickable {
                            onBackClick()
                        })
                Text(
                    text = title,
                    style = KTCTheme.typography.headlineSmallB,
                    color = White,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Composable
private fun TopButton(
    text: String, onClick: () -> Unit
) {
    Box(modifier = Modifier
        .border(BorderStroke(1.dp, White), RoundedCornerShape(20.dp))
        .background(DarkBlue)
        .clickable { onClick() }) {
        Text(
            text = text,
            color = White,
            style = KTCTheme.typography.titleNormalB,
            modifier = Modifier.padding(
                vertical = 4.dp, horizontal = 14.dp
            )
        )
    }
}

sealed interface TopAppBarType {
    object Default : TopAppBarType
    data class Button(val text: String, val onClick: () -> Unit) : TopAppBarType
    data class Custom(val content: @Composable () -> Unit) : TopAppBarType
}

@Preview
@Composable
fun HmAppbarPreview() {
    HmmTheme {
        HmAppBar(title = "만차로")
    }
}

@Preview
@Composable
fun HmAppbarTextButtonPreview() {
    HmmTheme {
        HmAppBar(title = "만차로", navigationType = TopAppBarType.Button("새로고침") {})
    }
}

@Preview
@Composable
fun HmAppbarCustomButtonPreview() {
    HmmTheme {
        HmAppBar(title = "만차로", navigationType = TopAppBarType.Custom {
            Row {
                Icon(imageVector = Icons.Default.Warning, contentDescription = "", tint = Red03)
                Text(text = "경고2", color = Red03)
            }
        })
    }
}