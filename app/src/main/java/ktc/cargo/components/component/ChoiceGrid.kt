package ktc.cargo.components.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ktc.cargo.components.etc.ComponentItem
import ktc.cargo.components.etc.ComponentItem.Companion.toComponentItems
import ktc.cargo.components.ui.theme.HmmTheme

@Composable
fun HmChoiceGrid(
    modifier: Modifier = Modifier,
    choices: List<ComponentItem>,
    columns: Int,
    type: HmButtonType = HmButtonType.Border,
    onClick: (ComponentItem) -> Unit = {},
) {
    LazyVerticalGrid(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        columns = GridCells.Fixed(columns),
        modifier = modifier
    ) {
        items(choices.size) { index ->
            HmChoiceButton(text = choices[index].text,
                type = type,
                isSelected = choices[index].selected,
                onClick = {
                    onClick(choices[index])
                })
        }
    }
}

@Preview
@Composable
fun ChoiceListPreview() {
    HmmTheme {
        HmChoiceGrid(
            choices = listOf(
                "카고", "윙바디", "카고/윙바디", "추레라", "탑", "다마스", "라보", "밴", "냉탑"
            ).toComponentItems(), columns = 3, type = HmButtonType.Fill
        )
    }
}