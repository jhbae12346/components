package com.example.myapplication.component.textfield

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.icon.MyIconPack
import com.example.myapplication.icon.myiconpack.Visibility
import com.example.myapplication.icon.myiconpack.VisibilityOff
import com.example.myapplication.ui.theme.BlackGray50
import com.example.myapplication.ui.theme.HmmTheme
import com.example.myapplication.ui.theme.KTCTheme
import com.example.myapplication.ui.theme.WhiteGray

@Composable
fun HmPasswordTextField(
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester = FocusRequester(),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    value: String,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = KTCTheme.typography.titleMediumB,
    hint: String = "",
) {
    val requester by remember { mutableStateOf(focusRequester) }
    var isFocus by remember {
        mutableStateOf(false)
    }

    var passwordVisibility by remember { mutableStateOf(false) }

    BasicTextField(
        value = value,
        textStyle = textStyle,
        keyboardOptions = keyboardOptions,
        onValueChange = onValueChange,
        singleLine = true,
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
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
                modifier = Modifier.padding(vertical = 8.5.dp, horizontal = 16.dp)
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

                val icon = if (passwordVisibility) MyIconPack.Visibility else MyIconPack.VisibilityOff
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        passwordVisibility = !passwordVisibility
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun HmPasswordTextFieldPreview() {
    HmmTheme {
        HmPasswordTextField(
            value = "a124124",
            onValueChange = {},
            hint = "인증번호 입력"
        )
    }
}