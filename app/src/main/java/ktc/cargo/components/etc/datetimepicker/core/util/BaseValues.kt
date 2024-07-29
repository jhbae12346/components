package ktc.cargo.driver.hmmdatetimepicker.datepicker.core.util

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

object BaseValues {

    val CONTENT_DEFAULT_PADDING: PaddingValues
        @Composable
        get() = PaddingValues(
            start = 24.dp,
            end = 24.dp,
            top = 24.dp
        )
}