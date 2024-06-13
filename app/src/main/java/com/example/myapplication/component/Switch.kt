package com.example.myapplication.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.myapplication.etc.BooleanProvider
import com.example.myapplication.ui.theme.DarkBlue
import com.example.myapplication.ui.theme.HmmTheme
import com.example.myapplication.ui.theme.KTCTheme
import com.example.myapplication.ui.theme.White
import com.example.myapplication.ui.theme.WhiteBlue

@Composable
fun HmSwitch(
    modifier: Modifier = Modifier,
    checked: Boolean,
    thumbColor: Color = White,
    checkedTrackColor: Color = DarkBlue,
    uncheckedTrackColor: Color = WhiteBlue,
    onCheckedChange: ((Boolean) -> Unit)?,
) {
    Switch(
        checked = checked,
        colors = SwitchDefaults.colors(
            checkedThumbColor = checkedTrackColor,
            checkedTrackColor = checkedTrackColor,
            uncheckedThumbColor = uncheckedTrackColor,
            uncheckedTrackColor = uncheckedTrackColor,
            uncheckedBorderColor = uncheckedTrackColor
        ),
        thumbContent = {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawCircle(
                    color = thumbColor,
                    radius = 28f,
                    center = center
                )
            }
        },
        onCheckedChange = onCheckedChange,
        modifier = modifier
    )
}

@Composable
fun HmTextSwitch(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = White,
    checked: Boolean,
    thumbColor: Color = DarkBlue,
    checkedTrackColor: Color = White,
    uncheckedTrackColor: Color = White,
    onCheckedChange: ((Boolean) -> Unit)?,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Text(text = text, color = textColor, style = KTCTheme.typography.titleNormalB)
        Spacer(modifier = Modifier.width(6.dp))
        Switch(
            checked = checked,
            colors = SwitchDefaults.colors(
                checkedThumbColor = checkedTrackColor,
                checkedTrackColor = checkedTrackColor,
                uncheckedThumbColor = uncheckedTrackColor,
                uncheckedTrackColor = uncheckedTrackColor,
                uncheckedBorderColor = uncheckedTrackColor
            ),
            thumbContent = {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawCircle(
                        color = thumbColor,
                        radius = 28f,
                        center = center
                    )
                }
            },
            onCheckedChange = onCheckedChange
        )
    }

}


@Preview(showBackground = true)
@Composable
private fun HmSwitchPreview(
    @PreviewParameter(BooleanProvider::class) checked: Boolean
) {
    HmmTheme {
        Column {
            HmSwitch(
                checked = checked
            ) {

            }
        }
    }
}

@Preview
@Composable
private fun HmTextSwitchPreview(
    @PreviewParameter(BooleanProvider::class) checked: Boolean
) {
    HmmTheme {
        Column {
            HmTextSwitch(
                text = "자동",
                checked = checked,
            ) {

            }
        }
    }
}