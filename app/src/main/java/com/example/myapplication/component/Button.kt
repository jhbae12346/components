package com.example.myapplication.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.myapplication.etc.BooleanProvider
import com.example.myapplication.icon.MyIconPack
import com.example.myapplication.icon.myiconpack.Modify
import com.example.myapplication.ui.theme.BlackGray
import com.example.myapplication.ui.theme.DarkBlue
import com.example.myapplication.ui.theme.HmmTheme
import com.example.myapplication.ui.theme.KTCTheme
import com.example.myapplication.ui.theme.Sky
import com.example.myapplication.ui.theme.White
import com.example.myapplication.ui.theme.WhiteBlue
import com.example.myapplication.ui.theme.WhiteGray

@Composable
fun HmLargeButton(
    modifier: Modifier = Modifier,
    text: String = "확인",
    color: Color = DarkBlue,
    textColor: Color = White,
    isActive: Boolean = true,
    onClick: () -> Unit
) {
    Column {
        Button(
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isActive) color else WhiteBlue, contentColor = textColor
            ),
            contentPadding = PaddingValues(vertical = 14.dp),
            onClick = onClick,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text = text, style = KTCTheme.typography.titleLargeB
            )
        }
    }
}

@Composable
fun HmRegularButton(
    modifier: Modifier = Modifier,
    text: String = "확인",
    color: Color = DarkBlue,
    textColor: Color = White,
    isActive: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isActive) color else WhiteBlue, contentColor = textColor
        ),
        contentPadding = PaddingValues(vertical = 10.dp),
        onClick = onClick,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            style = KTCTheme.typography.titleNormalB,
        )
    }
}

@Composable
fun HmSmallButton(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = BlackGray,
    textColor: Color = White,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(4.dp), onClick = onClick, color = color, modifier = modifier
    ) {
        Text(
            text = text,
            color = textColor,
            style = KTCTheme.typography.titleMediumB,
            modifier = Modifier.padding(
                vertical = 4.dp, horizontal = 10.dp
            )
        )
    }
}

enum class ChoiceButtonSelectedType {
    Fill, Border
}

@Composable
fun HmChoiceButton(
    text: String,
    type: ChoiceButtonSelectedType = ChoiceButtonSelectedType.Border,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    val backgroundColor by animateColorAsState(
        targetValue = when {
            isSelected && type == ChoiceButtonSelectedType.Fill -> Sky
            isSelected -> Sky.copy(alpha = 0.1f)
            else -> WhiteGray
        }, label = ""
    )

    val textColor by animateColorAsState(
        targetValue = when {
            isSelected && type == ChoiceButtonSelectedType.Fill -> White
            isSelected -> Sky
            else -> BlackGray
        }, label = ""
    )

    val borderStroke = if (isSelected && type == ChoiceButtonSelectedType.Border) {
        BorderStroke(2.dp, Sky)
    } else {
        null
    }

    Surface(
        shape = RoundedCornerShape(5.dp),
        onClick = onClick,
        color = backgroundColor,
        border = borderStroke,
    ) {
        Text(
            text = text,
            color = textColor,
            textAlign = TextAlign.Center,
            style = KTCTheme.typography.titleNormalB,
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 5.dp)
        )
    }
}

@Composable
fun HmSearchButton(
    modifier: Modifier = Modifier, onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(5.dp), onClick = onClick, color = Sky, modifier = modifier
    ) {
        Text(
            text = "검색",
            color = White,
            style = KTCTheme.typography.titleNormalB,
            modifier = Modifier.padding(
                vertical = 10.dp, horizontal = 18.5.dp
            )
        )
    }
}

@Composable
fun HmIconButton(
    modifier: Modifier = Modifier,
    text: String,
    icon: ImageVector,
    color: Color = WhiteGray,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(5.dp),
        onClick = onClick,
        color = color,
        modifier = modifier,
    ) {
        Row {
            Spacer(modifier = Modifier.width(12.dp))
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                textAlign = TextAlign.Center,
                style = KTCTheme.typography.titleMediumB,
                modifier = Modifier.padding(vertical = 10.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
        }

    }
}

@Preview
@Composable
fun HmLargeButtonPreview(
    @PreviewParameter(BooleanProvider::class) isActive: Boolean
) {
    HmmTheme {
        HmLargeButton(
            onClick = {},
            isActive = isActive,
        )
    }
}

@Preview
@Composable
fun HmRegularButtonPreview(
    @PreviewParameter(BooleanProvider::class) isActive: Boolean
) {
    HmmTheme {
        HmRegularButton(
            onClick = {}, isActive = isActive, modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview
@Composable
fun HmSmallButtonPreview() {
    HmmTheme {
        HmSmallButton(
            text = "계좌목록"
        ) {

        }
    }
}

@Preview
@Composable
fun HmChoiceButtonPreview(
    @PreviewParameter(BooleanProvider::class) isSelected: Boolean
) {
    HmmTheme {
        HmChoiceButton(
            text = "계좌목록", isSelected = isSelected
        ) {

        }
    }
}

@Preview
@Composable
fun HmSearchButtonPreview() {
    HmmTheme {
        HmSearchButton {

        }
    }
}

@Preview
@Composable
fun HmIconButtonPreview(
    @PreviewParameter(BooleanProvider::class) isSelected: Boolean
) {
    HmmTheme {
        HmIconButton(
            text = "계좌목록",
            icon = MyIconPack.Modify
        ) {

        }
    }
}