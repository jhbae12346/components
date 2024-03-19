package com.example.myapplication.timepicker

import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.myapplication.component.HmBottomScaffold
import com.example.myapplication.component.HmDefaultPopUp
import com.example.myapplication.component.HmSplitFullButton
import com.example.myapplication.component.HmWeightedRoundSplitButton
import com.example.myapplication.component.PopUpType
import com.example.myapplication.component.PopUpTypeProvider
import com.example.myapplication.component.ThemePreviews
import com.example.myapplication.timepicker.views.HmTimePicker
import com.example.myapplication.ui.theme.DarkGray
import com.example.myapplication.ui.theme.Hmm_mancharoTheme
import com.example.myapplication.ui.theme.KTCTheme
import com.example.myapplication.ui.theme.Sky
import com.example.myapplication.ui.theme.White
import com.example.myapplication.ui.theme.White2
import com.example.myapplication.ui.theme.surfaceDim
import java.time.LocalTime

/**
 *  rememberModalBottomSheetState(true)여야 합니다.
 * */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HmTimePicker(
    sheetState: SheetState,
    startTime: LocalTime = LocalTime.now(),
    popUpType: PopUpType = PopUpType.BottomSheet,
    onDismissRequest: () -> Unit,
    onTimeSelected: (LocalTime) -> Unit
) {
    var snappedTime by remember { mutableStateOf(startTime) }

    HmDefaultPopUp(
        sheetState = sheetState,
        popUpType = popUpType,
        onDismissRequest = onDismissRequest,
        content = {
            HmTimePicker(
                startTime = startTime
            ) { newTime ->
                snappedTime = newTime
            }
        },
        button = {
            HmWeightedRoundSplitButton(
                onLeftClick = onDismissRequest,
                onRightClick = {
                    onDismissRequest()
                    onTimeSelected(snappedTime)
                }
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HmTimePickerScaffold(
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
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surfaceDim)
                    .padding(horizontal = 16.dp, vertical = 30.dp)
            ) {
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
            }
        },
        bottomBar = bottomBar,
        content = content
    )
}

@Composable
fun Scrim(
    color: Color,
    onDismissRequest: () -> Unit,
    visible: Boolean
) {
    if (color.isSpecified) {
        val alpha by animateFloatAsState(
            targetValue = if (visible) 1f else 0f,
            animationSpec = TweenSpec(), label = ""
        )
        val dismissSheet = if (visible) {
            Modifier
                .pointerInput(onDismissRequest) {
                    detectTapGestures {
                        onDismissRequest()
                    }
                }
                .clearAndSetSemantics {}
        } else {
            Modifier
        }
        Canvas(
            Modifier
                .fillMaxSize()
                .then(dismissSheet)
        ) {
            drawRect(color = color, alpha = alpha)
        }
    }
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
fun HmTimePickerPreview(
    @PreviewParameter(PopUpTypeProvider::class) popUpType: PopUpType
) {
    Hmm_mancharoTheme {
        HmTimePicker(
            sheetState = SheetState(true, SheetValue.Expanded, { true }, false),
            popUpType = popUpType,
            onDismissRequest = {}
        ) {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@ThemePreviews
@Composable
fun HmTimePickerScaffoldPreview() {
    Hmm_mancharoTheme {
        val scaffoldState = rememberBottomSheetScaffoldState(
            SheetState(true, SheetValue.Expanded, { true }, false)
        )
        HmTimePickerScaffold(
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