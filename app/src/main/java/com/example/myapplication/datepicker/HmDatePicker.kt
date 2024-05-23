package com.example.myapplication.datepicker

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import com.example.myapplication.component.ThemePreviews
import com.example.myapplication.datepicker.models.CalendarConfig
import com.example.myapplication.datepicker.models.CalendarSelection
import com.example.myapplication.datepicker.models.CalendarStyle
import com.example.myapplication.datepicker.views.CalendarBottomSheet
import com.example.myapplication.ui.theme.HmmTheme
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HmDatePicker(
    scaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
    timeBoundary: ClosedRange<LocalDate> = LocalDate.now().let { now -> now..now.plusYears(1) },
    onDateSelect: (LocalDate) -> Unit,
    onDisMissRequest: () -> Unit,
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    CalendarBottomSheet(
        scaffoldState = scaffoldState,
        config = CalendarConfig(
            yearSelection = true,
            monthSelection = true,
            style = CalendarStyle.MONTH,
            boundary = timeBoundary
        ),
        selection = CalendarSelection.Date { newDates ->
            onDateSelect(newDates)
        },
        onDisMissRequest = onDisMissRequest,
        bottomBar = bottomBar,
        content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@ThemePreviews
@Composable
fun HmDatePickerPreview() {
    HmmTheme {
        val scaffoldState = rememberBottomSheetScaffoldState(
            SheetState(true, SheetValue.Expanded, { true }, false)
        )
        HmDatePicker(
            scaffoldState = scaffoldState,
            onDateSelect = {},
            onDisMissRequest = {},
            bottomBar = {},
            content = {}
        )
    }
}