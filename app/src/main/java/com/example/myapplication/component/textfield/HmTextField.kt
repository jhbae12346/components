package com.example.myapplication.component.textfield

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.icon.MyIconPack
import com.example.myapplication.icon.myiconpack.Modify
import com.example.myapplication.ui.theme.BlackGray50
import com.example.myapplication.ui.theme.HmmTheme
import com.example.myapplication.ui.theme.KTCTheme
import com.example.myapplication.ui.theme.WhiteGray

@Composable
fun HmTextField(
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester = FocusRequester(),
    value: String,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = KTCTheme.typography.titleMediumB,
    hint: String = "",
) {
    val requester by remember { mutableStateOf(focusRequester) }
    var isFocus by remember {
        mutableStateOf(false)
    }

    BasicTextField(
        value = value,
        textStyle = textStyle,
        onValueChange = onValueChange,
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .focusRequester(requester)
            .onFocusChanged {
                isFocus = it.isFocused
            }
    ) { innerTextField ->
        Surface(
            shape = RoundedCornerShape(5.dp),
            color = WhiteGray,
            border = if (isFocus) BorderStroke(2.dp, BlackGray50) else null
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.5.dp)
            ) {
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    if (value.isEmpty() && !isFocus) {
                        Text(
                            text = hint,
                            style = textStyle
                        )
                    }
                    innerTextField()
                }
                if (value.isEmpty() && !isFocus) {
                    Icon(
                        imageVector = MyIconPack.Modify,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun HmTextFieldPreview() {
    HmmTheme {
        HmTextField(
            value = "",
            onValueChange = {},
            hint = "인증번호 입력"
        )
    }
}