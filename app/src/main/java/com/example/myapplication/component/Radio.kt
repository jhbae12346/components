package com.example.myapplication.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.myapplication.etc.ComponentItem
import com.example.myapplication.etc.ComponentItem.Companion.toComponentItems
import com.example.myapplication.etc.BooleanProvider
import com.example.myapplication.ui.theme.Black
import com.example.myapplication.ui.theme.HmmTheme
import com.example.myapplication.ui.theme.KTCTheme
import com.example.myapplication.ui.theme.White
import com.example.myapplication.ui.theme.WhiteBlue

@Composable
fun HmRadioGroup(
    modifier: Modifier = Modifier,
    radioList: List<ComponentItem>,
    orientation: Orientation,
    verticalSpace: Dp = 10.dp,
    onClick: (ComponentItem) -> Unit,
) {
    var list by remember { mutableStateOf(radioList) }

    val onRadioClick: (ComponentItem) -> Unit = { selectRadio ->
        list = list.map {
            it.copy(selected = it.text == selectRadio.text)
        }
        onClick(selectRadio)
    }

    if (orientation == Orientation.Vertical) {
        Column(
            verticalArrangement = Arrangement.spacedBy(verticalSpace),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            Radios(list, onRadioClick)
        }
    } else {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
        ) {
            Radios(list, onRadioClick)
        }
    }
}

@Composable
private fun Radios(list: List<ComponentItem>, onRadioClick: (ComponentItem) -> Unit) {
    list.forEach {
        HmRadio(text = it.text, selected = it.selected, onClick = {
            onRadioClick(it)
        })
    }
}

@Composable
fun HmRadio(
    modifier: Modifier = Modifier, text: String = "", selected: Boolean = false, onClick: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier.clickable {
        onClick()
    }) {
        Canvas(
            modifier = Modifier.size(26.dp)
        ) {
            drawCircle(
                color = WhiteBlue, radius = 40f, center = center
            )
            drawCircle(
                color = White, radius = 35f, center = center
            )
            drawCircle(
                color = if (selected) Black else WhiteBlue, radius = 22f, center = center
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            color = if (selected) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.surfaceVariant,
            textAlign = TextAlign.Center,
            style = if (selected) KTCTheme.typography.titleNormalM else KTCTheme.typography.titleNormalR
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HmRadioPreview(
    @PreviewParameter(BooleanProvider::class) isSelected: Boolean
) {
    HmmTheme {
        HmRadio(
            text = "자동", selected = isSelected
        ) {}
    }
}

@Preview
@Composable
private fun HmRadioGroupVerticalPreview() {
    HmmTheme {
        HmRadioGroup(
            radioList = listOf("자동", "주간", "야간").toComponentItems(),
            orientation = Orientation.Vertical,
        ) {}
    }
}

@Preview
@Composable
private fun HmRadioGroupHorizontalPreview() {
    HmmTheme {
        HmRadioGroup(
            radioList = listOf("자동", "주간", "야간").toComponentItems(),
            orientation = Orientation.Horizontal,
        ) {}
    }
}

