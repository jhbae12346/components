package com.example.myapplication.datepicker.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties
import com.example.myapplication.datepicker.core.base.BottomSheetBase
import com.example.myapplication.datepicker.models.CalendarConfig
import com.example.myapplication.datepicker.models.CalendarSelection

@ExperimentalMaterial3Api
@Composable
internal fun CalendarBottomSheet(
    scaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
    selection: CalendarSelection,
    config: CalendarConfig = CalendarConfig(),
    onDisMissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit = {},
) {
    BottomSheetBase(
        scaffoldState = scaffoldState,
        properties = properties,
        onDisMissRequest = onDisMissRequest,
        bottomBar = bottomBar,
        content = content
    ) {
        CalendarView(
            config = config,
            onDisMissRequest = onDisMissRequest,
            selection = selection,
        )
    }
}