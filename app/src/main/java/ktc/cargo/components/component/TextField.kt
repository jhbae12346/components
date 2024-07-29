package ktc.cargo.components.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ktc.cargo.components.icon.MyIconPack
import ktc.cargo.components.icon.myiconpack.Modify
import ktc.cargo.components.icon.myiconpack.Visibility
import ktc.cargo.components.icon.myiconpack.VisibilityOff
import ktc.cargo.components.ui.theme.BlackGrayA50
import ktc.cargo.components.ui.theme.HmmTheme
import ktc.cargo.components.ui.theme.KTCTheme

private val textFieldBorderColor = BlackGrayA50

@Composable
fun HmDefaultTextField(
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester = FocusRequester(),
    value: String,
    hint: String = "",
    textStyle: TextStyle = KTCTheme.typography.titleMediumB,
    onValueChange: (String) -> Unit,
) {
    val requester by remember { mutableStateOf(focusRequester) }
    var isFocus by remember { mutableStateOf(false) }

    BasicTextField(
        value = value,
        textStyle = textStyle,
        onValueChange = onValueChange,
        singleLine = true,
        modifier = modifier
            .focusRequester(requester)
            .onFocusChanged {
                isFocus = it.isFocused
            }
    ) { innerTextField ->
        Surface(
            shape = RoundedCornerShape(5.dp),
            color = MaterialTheme.colorScheme.secondaryContainer,
            border = if (isFocus) BorderStroke(2.dp, textFieldBorderColor) else null
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
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
            }
        }
    }
}

@Composable
fun HmPasswordTextField(
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester = FocusRequester(),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    value: String,
    hint: String = "",
    textStyle: TextStyle = KTCTheme.typography.titleMediumB,
    onValueChange: (String) -> Unit,
) {
    val requester by remember { mutableStateOf(focusRequester) }
    var isFocus by remember { mutableStateOf(false) }
    var passwordVisibility by remember { mutableStateOf(false) }

    BasicTextField(
        value = value,
        textStyle = textStyle,
        keyboardOptions = keyboardOptions,
        onValueChange = onValueChange,
        singleLine = true,
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        modifier = modifier
            .focusRequester(requester)
            .onFocusChanged {
                isFocus = it.isFocused
            }
    ) { innerTextField ->
        Surface(
            shape = RoundedCornerShape(5.dp),
            color = MaterialTheme.colorScheme.secondaryContainer,
            border = if (isFocus) BorderStroke(2.dp, textFieldBorderColor) else null
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
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

// TODO: 수정의 경우 어떤 방식으로 수정을 하게 만들 것인지 논의가 필요함
@Composable
fun HmModifyTextField(
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester = FocusRequester(),
    value: String,
    onValueChange: (String) -> Unit
) {
    val requester by remember { mutableStateOf(focusRequester) }
    var isFocus by remember {
        mutableStateOf(false)
    }
    var modifyAbility by remember { mutableStateOf(false) }

    BasicTextField(
        value = value,
        textStyle = KTCTheme.typography.titleMediumB,
        onValueChange = onValueChange,
        singleLine = true,
        modifier = modifier
            .focusRequester(requester)
            .onFocusChanged {
                isFocus = it.isFocused
            }
    ) { innerTextField ->
        Surface(
            shape = RoundedCornerShape(5.dp),
            color = MaterialTheme.colorScheme.secondaryContainer,
            border = if (isFocus) BorderStroke(2.dp, textFieldBorderColor) else null
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    if (value.isEmpty() && !isFocus) {
                        Text(
                            text = "수정",
                            style = KTCTheme.typography.titleMediumB
                        )
                    }
                    innerTextField()
                }
                Icon(
                    imageVector = MyIconPack.Modify,
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
fun DefaultTextFieldPreview() {
    HmmTheme {
        HmDefaultTextField(
            value = "",
            onValueChange = {},
            hint = "인증번호 입력",
            modifier = Modifier
        )
    }
}

@Preview
@Composable
private fun PasswordTextFieldPreview() {
    HmmTheme {
        HmPasswordTextField(
            value = "a124124",
            onValueChange = {},
            hint = "인증번호 입력",
            modifier = Modifier
        )
    }
}

@Preview
@Composable
fun ModifyTextFieldPreview() {
    HmmTheme {
        HmModifyTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
        )
    }
}