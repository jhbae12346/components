package ktc.cargo.components.component

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
import ktc.cargo.components.ui.theme.DarkBlue
import ktc.cargo.components.ui.theme.HmmTheme
import ktc.cargo.components.ui.theme.KTCTheme
import ktc.cargo.components.ui.theme.Red03
import ktc.cargo.components.ui.theme.White1

@Composable
fun HmTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationType: HmTopAppBarType = HmTopAppBarType.Default,
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
                    HmTopAppBarType.Default -> {}
                    is HmTopAppBarType.Custom -> navigationType.content()
                    is HmTopAppBarType.Button -> TopButton(
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
                    tint = White1,
                    modifier = Modifier
                        .size(34.dp)
                        .clickable {
                            onBackClick()
                        })
                Text(
                    text = title,
                    style = KTCTheme.typography.headlineSmallB,
                    color = White1,
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
        .border(BorderStroke(1.dp, White1), RoundedCornerShape(20.dp))
        .background(DarkBlue)
        .clickable { onClick() }) {
        Text(
            text = text,
            color = White1,
            style = KTCTheme.typography.titleNormalB,
            modifier = Modifier.padding(
                vertical = 4.dp, horizontal = 14.dp
            )
        )
    }
}

sealed interface HmTopAppBarType {
    data object Default : HmTopAppBarType
    data class Button(val text: String, val onClick: () -> Unit) : HmTopAppBarType
    data class Custom(val content: @Composable () -> Unit) : HmTopAppBarType
}

@Preview
@Composable
fun TopAppbarPreview() {
    HmmTheme {
        HmTopAppBar(title = "만차로")
    }
}

@Preview
@Composable
fun AppbarTextButtonPreview() {
    HmmTheme {
        HmTopAppBar(title = "만차로", navigationType = HmTopAppBarType.Button("새로고침") {})
    }
}

@Preview
@Composable
fun AppbarCustomButtonPreview() {
    HmmTheme {
        HmTopAppBar(title = "만차로", navigationType = HmTopAppBarType.Custom {
            Row {
                Icon(imageVector = Icons.Default.Warning, contentDescription = "", tint = Red03)
                Text(text = "경고2", color = Red03)
            }
        })
    }
}