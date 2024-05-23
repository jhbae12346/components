package com.example.myapplication.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.BlackGray
import com.example.myapplication.ui.theme.DarkBlue
import com.example.myapplication.ui.theme.HmmTheme
import com.example.myapplication.ui.theme.KTCTheme
import com.example.myapplication.ui.theme.Sky
import com.example.myapplication.ui.theme.White
import com.example.myapplication.ui.theme.WhiteBlue

@Composable
fun HmFullButton(
    modifier: Modifier = Modifier,
    text: String = "확인",
    color: Color = DarkBlue,
    isActive: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isActive) color else WhiteBlue,
            contentColor = White
        ),
        contentPadding = PaddingValues(vertical = 14.dp),
        onClick = onClick,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            style = KTCTheme.typography.titleLargeB
        )
    }
}

@Composable
fun HmSplitFullButton(
    modifier: Modifier = Modifier,
    leftText: String = "취소",
    rightText: String = "확인",
    color: Color = DarkBlue,
    onLeftClick: () -> Unit,
    onRightClick: () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Button(
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = WhiteBlue,
                contentColor = White
            ),
            contentPadding = PaddingValues(vertical = 14.dp),
            onClick = onLeftClick,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = leftText,
                style = KTCTheme.typography.titleLargeB
            )
        }
        Button(
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = color,
                contentColor = White
            ),
            contentPadding = PaddingValues(vertical = 14.dp),
            onClick = onRightClick,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = rightText,
                style = KTCTheme.typography.titleLargeB,
            )
        }
    }
}

@Composable
fun HmRoundButton(
    modifier: Modifier = Modifier,
    text: String = "확인",
    color: Color = DarkBlue,
    isActive: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isActive) color else WhiteBlue,
            contentColor = White
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
fun HmWeightedRoundSplitButton(
    modifier: Modifier = Modifier,
    color: Color = DarkBlue,
    isActive: Boolean = true,
    leftText: String = "취소",
    rightText: String = "확인",
    onLeftClick: () -> Unit,
    onRightClick: () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Button(
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = WhiteBlue,
                contentColor = White
            ),
            contentPadding = PaddingValues(vertical = 10.dp),
            onClick = onLeftClick,
            modifier = Modifier.weight(3f)
        ) {
            Text(
                text = leftText,
                style = KTCTheme.typography.titleNormalB
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Button(
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isActive) color else WhiteBlue,
                contentColor = White
            ),
            contentPadding = PaddingValues(vertical = 10.dp),
            onClick = onRightClick,
            modifier = Modifier.weight(5f)
        ) {
            Text(
                text = rightText,
                style = KTCTheme.typography.titleNormalB,
            )
        }
    }
}

@Composable
fun HmVerticalRoundSplitButton(
    modifier: Modifier = Modifier,
    leftText: String = "취소",
    rightText: String = "확인",
    color: Color = DarkBlue,
    onLeftClick: () -> Unit,
    onRightClick: () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Button(
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = WhiteBlue,
                contentColor = White
            ),
            contentPadding = PaddingValues(vertical = 10.dp),
            onClick = onLeftClick,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = leftText,
                style = KTCTheme.typography.titleNormalB
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Button(
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = color,
                contentColor = White
            ),
            contentPadding = PaddingValues(vertical = 10.dp),
            onClick = onRightClick,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = rightText,
                style = KTCTheme.typography.titleNormalB,
            )
        }
    }
}

@Composable
fun HmHorizontalRoundSplitButton(
    modifier: Modifier = Modifier,
    leftText: String = "취소",
    rightText: String = "확인",
    color: Color = DarkBlue,
    onLeftClick: () -> Unit,
    onRightClick: () -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        Button(
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = WhiteBlue,
                contentColor = White
            ),
            contentPadding = PaddingValues(vertical = 10.dp),
            onClick = onLeftClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = leftText,
                style = KTCTheme.typography.titleNormalB
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = color,
                contentColor = White
            ),
            contentPadding = PaddingValues(vertical = 10.dp),
            onClick = onRightClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = rightText,
                style = KTCTheme.typography.titleNormalB,
            )
        }
    }
}

@Composable
fun HmBlackButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(4.dp),
        onClick = onClick,
        color = BlackGray,
        modifier = modifier
    ) {
        Text(
            text = text,
            color = White,
            style = KTCTheme.typography.titleMediumB,
            modifier = Modifier.padding(
                vertical = 4.dp, horizontal = 10.dp
            )
        )
    }
}

@Composable
fun HmSearchButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(5.dp),
        onClick = onClick,
        color = Sky,
        modifier = modifier
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



@Preview
@Composable
fun HmFullButtonPreview(
    @PreviewParameter(BooleanProvider::class) isActive: Boolean
) {
    HmmTheme {
        HmFullButton(
            onClick = {},
            isActive = isActive,
        )
    }
}

@Preview
@Composable
fun HmSplitFullButtonPreview() {
    HmmTheme {
        HmSplitFullButton(
            onLeftClick = {},
            onRightClick = {},
        )
    }
}

@Preview
@Composable
fun HmRoundButtonPreview(
    @PreviewParameter(BooleanProvider::class) isActive: Boolean
) {
    HmmTheme {
        HmRoundButton(
            onClick = {},
            isActive = isActive,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview
@Composable
fun HmWeightedRoundSplitButtonPreview() {
    HmmTheme {
        HmWeightedRoundSplitButton(
            onLeftClick = {},
            onRightClick = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview
@Composable
fun HmVerticalRoundSplitButtonPreview() {
    HmmTheme {
        HmVerticalRoundSplitButton(
            onLeftClick = {},
            onRightClick = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview
@Composable
fun HmHorizontalRoundSplitButtonPreview() {
    HmmTheme {
        HmHorizontalRoundSplitButton(
            onLeftClick = {},
            onRightClick = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview
@Composable
fun HmBlackButtonPreview() {
    HmmTheme {
        HmBlackButton(
            text = "계좌목록"
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
