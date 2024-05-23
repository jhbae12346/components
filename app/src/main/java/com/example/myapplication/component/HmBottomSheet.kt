package com.example.myapplication.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import com.example.myapplication.timepicker.views.HmTimePicker

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HmBottomSheet(
    scaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
    onDismissRequest: () -> Unit,
    sheetContent: @Composable ColumnScope.() -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    HmBottomScaffold(
        scaffoldState = scaffoldState,
        onDismissRequest = onDismissRequest,
        sheetContent = {

        },
        bottomBar = bottomBar,
        content = content
    )
}