package com.example.myapplication.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.BlackGray
import com.example.myapplication.ui.theme.HmmTheme
import com.example.myapplication.ui.theme.KTCTheme
import com.example.myapplication.ui.theme.Sky
import com.example.myapplication.ui.theme.White
import com.example.myapplication.ui.theme.WhiteGray

enum class ChoiceButtonType {
    FullBlue, BorderBlue
}

data class ChoiceItem(
    val title: String, val isSelected: Boolean = false
) {
    companion object {
        fun List<String>.toChoiceItems() = mapIndexed { index, s ->
            ChoiceItem(
                title = s,
                isSelected = index == 0
            )
        }
    }
}

@Composable
fun HmChoiceList(
    modifier: Modifier = Modifier,
    choices: List<ChoiceItem>,
    columns: Int,
    type: ChoiceButtonType = ChoiceButtonType.BorderBlue,
    onClick: (Int) -> Unit = {},
) {
    LazyVerticalGrid(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(11.dp),
        columns = GridCells.Fixed(columns),
        modifier = modifier
    ) {
        items(choices.size) { index ->
            HmChoiceButton(
                text = choices[index].title,
                type = type,
                isSelected = choices[index].isSelected,
                onClick = {
                    onClick(index)
                }
            )
        }
    }
}

@Composable
fun HmChoiceButton(
    text: String,
    type: ChoiceButtonType = ChoiceButtonType.BorderBlue,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    if (isSelected) {
        when (type) {
            ChoiceButtonType.FullBlue -> {
                Surface(
                    shape = RoundedCornerShape(5.dp),
                    onClick = onClick,
                    color = Sky,
                ) {
                    Text(
                        text = text,
                        color = White,
                        textAlign = TextAlign.Center,
                        style = KTCTheme.typography.titleNormalB,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                }
            }
            ChoiceButtonType.BorderBlue -> {
                Surface(
                    shape = RoundedCornerShape(5.dp),
                    border = BorderStroke(2.dp, Sky),
                    onClick = onClick,
                    color = Sky.copy(alpha = 0.1f),
                ) {
                    Text(
                        text = text,
                        color = Sky,
                        textAlign = TextAlign.Center,
                        style = KTCTheme.typography.titleNormalB,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                }
            }
        }
    } else {
        Surface(
            shape = RoundedCornerShape(5.dp),
            onClick = onClick,
            color = WhiteGray,
        ) {
            Text(
                text = text,
                color = BlackGray,
                textAlign = TextAlign.Center,
                style = KTCTheme.typography.titleNormalB,
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }
    }
}

@Composable
fun HmFullChoiceButton(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(5.dp),
        border = if (isSelected) BorderStroke(2.dp, Sky) else null,
        onClick = onClick,
        color = if (isSelected) Sky.copy(alpha = 0.1f) else WhiteGray,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            color = if (isSelected) Sky else BlackGray,
            textAlign = TextAlign.Center,
            style = KTCTheme.typography.titleNormalB,
            modifier = Modifier.padding(vertical = 10.dp)
        )
    }
}

@Preview
@Composable
fun HmChoiceListPreview() {
    HmmTheme {
        HmChoiceList(
            choices = listOf(
                ChoiceItem("카고", true),
                ChoiceItem("윙바디"),
                ChoiceItem("카고/윙바디"),
                ChoiceItem("추레라"),
                ChoiceItem("탑"),
                ChoiceItem("다마스"),
                ChoiceItem("라보"),
                ChoiceItem("밴"),
                ChoiceItem("냉탑"),
            ),
            columns = 3
        )
    }
}

@Preview
@Composable
fun HmFullChoiceButtonPreview() {
    HmmTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            HmFullChoiceButton(
                text = "기사구함",
                isSelected = true
            ) {}
            HmFullChoiceButton(
                text = "차량사고/팔고",
            ) {}
        }
    }
}
