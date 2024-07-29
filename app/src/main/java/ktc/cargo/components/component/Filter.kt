package ktc.cargo.components.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import ktc.cargo.components.etc.BooleanProvider
import ktc.cargo.components.etc.ComponentItem
import ktc.cargo.components.etc.ComponentItem.Companion.toComponentItems
import ktc.cargo.components.ui.theme.BlackGrayA50
import ktc.cargo.components.ui.theme.HmmTheme
import ktc.cargo.components.ui.theme.KTCTheme
import ktc.cargo.components.ui.theme.Blue10

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
            HmFilterChip(
                text = item.text,
                selected = item.selected,
                onClick = {
                    onClick(item)
                }
            )
        }
    }
}

@Composable
fun HmFilterChip(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val selectedColor = Blue10.copy(alpha = 0.1f)
    FilterChip(
        selected = selected,
        onClick = onClick,
        label = {
            Text(
                text = text,
                style = KTCTheme.typography.titleMediumB,
                color = if (selected) Blue10 else BlackGrayA50
            )
        },
        shape = CircleShape,
        colors = FilterChipDefaults.filterChipColors(
            containerColor = Color.Transparent,
            selectedContainerColor = selectedColor
        ),
        border = FilterChipDefaults.filterChipBorder(
            selected = selected,
            enabled = true,
            borderColor = BlackGrayA50,
            selectedBorderColor = Blue10,
            borderWidth = 2.dp,
            selectedBorderWidth = 2.dp
        ),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun FilterPreview(
    @PreviewParameter(BooleanProvider::class) isSelected: Boolean
) {
    HmmTheme {
        HmFilterChip(selected = isSelected, text = "전체") {}
    }
}

@Preview
@Composable
private fun FilterGroupPreview() {
    HmmTheme {
        HmFilterGroup(
            filterList = listOf("전체", "미지원", "지원완료").toComponentItems()
        ) {

        }
    }
}