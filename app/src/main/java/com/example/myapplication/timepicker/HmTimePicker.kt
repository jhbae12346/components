package com.example.myapplication.timepicker

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.component.HmBottomScaffold
import com.example.myapplication.component.HmSplitFullButton
import com.example.myapplication.component.HmWeightedRoundSplitButton
import com.example.myapplication.component.ThemePreviews
import com.example.myapplication.timepicker.views.HmTimePicker
import com.example.myapplication.ui.theme.DarkGray
import com.example.myapplication.ui.theme.HmmTheme
import com.example.myapplication.ui.theme.KTCTheme
import com.example.myapplication.ui.theme.Sky
import com.example.myapplication.ui.theme.White
import com.example.myapplication.ui.theme.White2
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HmTimePicker(
    scaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
    startTime: LocalTime = LocalTime.now(),
    onDismissRequest: () -> Unit,
    onTimeSelected: (LocalTime) -> Unit,
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    var snappedTime by remember { mutableStateOf(startTime) }

    HmBottomScaffold(
        scaffoldState = scaffoldState,
        onDismissRequest = onDismissRequest,
        sheetContent = {
            HmTimePicker(
                startTime = snappedTime,
            ) { newTime ->
                snappedTime = newTime
            }

            HmWeightedRoundSplitButton(
                onLeftClick = onDismissRequest,
                onRightClick = {
                    onDismissRequest()
                    onTimeSelected(
                        snappedTime
                    )
                }
            )
        },
        bottomBar = bottomBar,
        content = content
    )
}

@Composable
fun HmAmPmButton(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        onClick = onClick,
        color = if (isSelected) Sky else White2,
        shadowElevation = if (isSelected) 0.dp else 4.dp,
        modifier = modifier
    ) {
        Text(
            text = text,
            color = if (isSelected) White else DarkGray,
            style = KTCTheme.typography.titleLargeB,
            modifier = Modifier.padding(10.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@ThemePreviews
@Composable
fun HmTimePickerScaffoldPreview() {
    HmmTheme {
        val scaffoldState = rememberBottomSheetScaffoldState(
            SheetState(true, SheetValue.Expanded, { true }, false)
        )
        HmTimePicker(
            scaffoldState = scaffoldState,
            onDismissRequest = {},
            onTimeSelected = {},
            bottomBar = {
                HmSplitFullButton(
                    onLeftClick = {
                    }) {
                }
            }
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = "테스트", Modifier.align(Alignment.Center))
            }
        }
    }
}