package ktc.cargo.components.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
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
import ktc.cargo.components.etc.BooleanProvider
import ktc.cargo.components.icon.MyIconPack
import ktc.cargo.components.icon.myiconpack.Modify
import ktc.cargo.components.ui.theme.BlackGray
import ktc.cargo.components.ui.theme.DarkBlue
import ktc.cargo.components.ui.theme.HmmTheme
import ktc.cargo.components.ui.theme.KTCTheme
import ktc.cargo.components.ui.theme.Blue10
import ktc.cargo.components.ui.theme.White1
import ktc.cargo.components.ui.theme.DarkBlue90
import ktc.cargo.components.ui.theme.Gray9

@Composable
fun HmLargeButton(
    modifier: Modifier = Modifier,
    text: String = "확인",
    color: Color = DarkBlue,
    inActiveColor: Color = DarkBlue90,
    textColor: Color = White1,
    isActive: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isActive) color else inActiveColor, contentColor = textColor
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

@Composable
fun HmRegularButton(
    modifier: Modifier = Modifier,
    text: String = "확인",
    color: Color = DarkBlue,
    inActiveColor: Color = DarkBlue90,
    textColor: Color = White1,
    isActive: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isActive) color else inActiveColor, contentColor = textColor
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
    textColor: Color = White1,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(4.dp),
        onClick = onClick,
        color = color,
        modifier = modifier
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

enum class HmButtonType {
    Fill, Border
}

@Composable
fun HmChoiceButton(
    text: String,
    type: HmButtonType = HmButtonType.Border,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    val backgroundColor by animateColorAsState(
        targetValue = when {
            isSelected && type == HmButtonType.Fill -> Blue10
            isSelected -> Blue10.copy(alpha = 0.1f)
            else -> Gray9
        }, label = ""
    )

    val textColor by animateColorAsState(
        targetValue = when {
            isSelected && type == HmButtonType.Fill -> White1
            isSelected -> Blue10
            else -> BlackGray
        }, label = ""
    )

    val borderStroke = if (isSelected && type == HmButtonType.Border) {
        BorderStroke(2.dp, Blue10)
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
        shape = RoundedCornerShape(5.dp), onClick = onClick, color = Blue10, modifier = modifier
    ) {
        Text(
            text = "검색",
            color = White1,
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
    color: Color = Gray9,
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
fun LargeButtonPreview(
    @PreviewParameter(BooleanProvider::class) isActive: Boolean
) {
    HmmTheme {
        HmLargeButton(
            onClick = {}
        )
    }
}

@Preview
@Composable
fun RegularButtonPreview(
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
fun SmallButtonPreview() {
    HmmTheme {
        HmSmallButton(
            text = "계좌목록"
        ) {

        }
    }
}

@Preview
@Composable
fun ChoiceButtonPreview(
    @PreviewParameter(BooleanProvider::class) isSelected: Boolean
) {
    HmmTheme {
        HmChoiceButton(
            text = "계좌목록",
            isSelected = isSelected
        ) {

        }
    }
}

@Preview
@Composable
fun SearchButtonPreview() {
    HmmTheme {
        HmSearchButton {

        }
    }
}

@Preview
@Composable
fun IconButtonPreview(
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