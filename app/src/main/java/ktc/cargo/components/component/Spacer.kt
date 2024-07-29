package ktc.cargo.components.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ktc.cargo.components.ui.theme.HmmTheme
import ktc.cargo.components.ui.theme.Gray7

@Composable
@NonRestartableComposable
fun HmSpacer(
    modifier: Modifier = Modifier
) {
    Spacer(
        modifier = modifier
            .fillMaxWidth()
            .height(3.dp)
            .background(Gray7)
    )
}

@Preview
@Composable
private fun SpacerPreview() {
    HmmTheme {
        HmSpacer()
    }
}