package com.example.myapplication.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.myapplication.ui.theme.HmmTheme
import com.example.myapplication.ui.theme.KTCTheme
import com.example.myapplication.ui.theme.surfaceDim

@Composable
fun HmDialog(
    header: @Composable () -> Unit = {},
    content: @Composable () -> Unit = {},
    button: @Composable () -> Unit = {},
    onDismissRequest: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surfaceDim)
                    .padding(horizontal = 16.dp, vertical = 30.dp)
            ) {
                header()
                Box(
                    modifier = Modifier.padding(top = 20.dp, bottom = 30.dp)
                 ) {
                    content()
                }
                button()
            }
        }
    }
}

@Composable
fun HmTextDialog(
    title: String,
    content: String,
    button: @Composable () -> Unit,
    onDismissRequest: () -> Unit
) {
    HmDialog(
        onDismissRequest = onDismissRequest,
        header = {
            Text(
                text = title,
                style = KTCTheme.typography.headlineMediumB,
            )
        },
        button = button,
        content = {
            Text(
                text = content,
                style = KTCTheme.typography.titleLargeM
            )
        }
    )
}

@Composable
fun HmTitleDialog(
    title: String,
    content: @Composable () -> Unit,
    button: @Composable () -> Unit,
    onDismissRequest: () -> Unit
) {
    HmDialog(
        onDismissRequest = onDismissRequest,
        header = {
            Text(
                text = title,
                style = KTCTheme.typography.headlineMediumB
            )
        },
        button = button,
        content = content
    )
}

@ThemePreviews
@Composable
private fun HmTextDialogPreview() {
    HmmTheme {
        HmTextDialog(
            title = "배차요청",
            content = "배차요청을 하시겠습니까?",
            button = {
                HmWeightedRoundSplitButton(
                    onLeftClick = {},
                    onRightClick = {},
                    modifier = Modifier
                )
            },
            onDismissRequest = {}
        )
    }
}

@ThemePreviews
@Composable
private fun HmTitleDialogPreview() {
    HmmTheme {
        HmTitleDialog(
            title = "배차요청",
            content = {
                HmChoiceList(
                    choices = listOf(
                        ChoiceItem("카고", true),
                        ChoiceItem("윙바디"),
                        ChoiceItem("카고/윙바디"),
                        ChoiceItem("추레라"),
                        ChoiceItem("탑"),
                        ChoiceItem("밴"),
                        ChoiceItem("냉탑"),
                    )
                )
            },
            button = {
                HmWeightedRoundSplitButton(
                    onLeftClick = {},
                    onRightClick = {},
                    modifier = Modifier
                )
            },
            onDismissRequest = {}
        )
    }
}