package com.example.myapplication.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.myapplication.etc.BooleanProvider
import com.example.myapplication.etc.ComponentItem
import com.example.myapplication.etc.ComponentItem.Companion.toComponentItems
import com.example.myapplication.ui.theme.BlackGray50
import com.example.myapplication.ui.theme.HmmTheme
import com.example.myapplication.ui.theme.KTCTheme
import com.example.myapplication.ui.theme.Sky

@Composable
fun HmFilterGroup(
    modifier: Modifier = Modifier,
    filterList: List<ComponentItem>,
    horizontalSpace: Dp = 10.dp,
    onClick: (ComponentItem) -> Unit,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(horizontalSpace),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        items(filterList) { item ->
            HmFilter(
                text = item.text,
                selected = item.selected,
                onClick = {
                    onClick(item)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HmFilter(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val selectedColor = Sky.copy(alpha = 0.1f)

    FilterChip(
        selected = selected,
        onClick = onClick,
        label = {
            Text(
                text = text,
                style = KTCTheme.typography.titleMediumB,
                color = if (selected) Sky else BlackGray50
            )
        },
        shape = CircleShape,
        colors = FilterChipDefaults.filterChipColors(
            containerColor = Color.Transparent,
            selectedContainerColor = selectedColor
        ),
        border = FilterChipDefaults.filterChipBorder(
            borderColor = BlackGray50,
            selectedBorderColor = Sky,
            borderWidth = 2.dp,
            selectedBorderWidth = 2.dp
        ),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun HmFilterPreview(
    @PreviewParameter(BooleanProvider::class) isSelected: Boolean
) {
    HmmTheme {
        HmFilter(selected = isSelected, text = "전체") {}
    }
}

@Preview
@Composable
private fun HmFilterGroupPreview() {
    HmmTheme {
        HmFilterGroup(
            filterList = listOf("전체", "미지원", "지원완료").toComponentItems()
        ) {

        }
    }
}