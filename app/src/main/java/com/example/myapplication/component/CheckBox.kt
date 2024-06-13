package com.example.myapplication.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.HmmTheme
import com.example.myapplication.ui.theme.KTCTheme
import com.example.myapplication.ui.theme.LightGray
import com.example.myapplication.ui.theme.Sky

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
                checkedColor = Sky,
                uncheckedColor = LightGray
            ),
            onCheckedChange = onCheckedChange
        )
        Text(text = text, style = KTCTheme.typography.titleNormalM, modifier = Modifier.padding(end = 15.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun HmCheckBoxPreview() {
    HmmTheme {
        HmCheckBox(
            text = "가입자 정보 동일"
        ) {

        }
    }
}