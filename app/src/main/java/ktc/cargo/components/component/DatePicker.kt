package ktc.cargo.components.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ktc.cargo.components.etc.datetimepicker.models.CalendarConfig
import ktc.cargo.components.etc.datetimepicker.models.CalendarSelection
import ktc.cargo.components.etc.datetimepicker.views.CalendarBottomSheet
import ktc.cargo.components.ui.theme.HmmTheme
import ktc.cargo.driver.hmmdatetimepicker.datepicker.models.CalendarStyle
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HmDatePicker(
    sheetState: SheetState = rememberModalBottomSheetState(true),
    selectedDate: LocalDate = LocalDate.now(),
    timeBoundary: ClosedRange<LocalDate> = LocalDate.now().let { now -> now..now.plusYears(1) },
    onDismissRequest: () -> Unit,
    onDateSelect: (LocalDate) -> Unit,
) {
    CalendarBottomSheet(
        sheetState = sheetState,
        config = CalendarConfig(
            yearSelection = true,
            monthSelection = true,
            style = CalendarStyle.MONTH,
            boundary = timeBoundary
        ),
        selection = CalendarSelection.Date(
            selectedDate = selectedDate
        ) { newDates ->
            onDateSelect(newDates)
        },
        onDisMissRequest = onDismissRequest
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun HmDatePickerPreview() {
    HmmTheme {
        HmDatePicker(
            sheetState = SheetState(true, SheetValue.Expanded, { true }, false),
            onDateSelect = {},
            onDismissRequest = {},
        )
    }
}