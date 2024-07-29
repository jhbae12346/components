package ktc.cargo.components.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import ktc.cargo.components.etc.BooleanProvider
import ktc.cargo.components.ui.theme.HmmTheme
import ktc.cargo.components.ui.theme.KTCTheme
import ktc.cargo.components.ui.theme.Gray8
import ktc.cargo.components.ui.theme.Blue10

@Composable
fun HmCheckBox(
    modifier: Modifier = Modifier,
    text: String = "",
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Checkbox(
            checked = checked,
            colors = CheckboxDefaults.colors(
                checkedColor = Blue10,
                uncheckedColor = Gray8
            ),
            onCheckedChange = onCheckedChange
        )
        if (text.isNotEmpty())
            Text(text = text, style = KTCTheme.typography.titleNormalM, modifier = Modifier.padding(end = 15.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun CheckBoxPreview(
    @PreviewParameter(BooleanProvider::class) checked: Boolean
) {
    HmmTheme {
        HmCheckBox(
            checked = checked,
            text = "가입자 정보 동일"
        ) {

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyCheckBoxPreview(
    @PreviewParameter(BooleanProvider::class) checked: Boolean
) {
    HmmTheme {
        HmCheckBox(
            checked = checked,
            text = ""
        ) {

        }
    }
}