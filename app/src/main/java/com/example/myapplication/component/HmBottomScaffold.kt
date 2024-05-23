package com.example.myapplication.component

import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.DarkGray
import com.example.myapplication.ui.theme.HmmTheme
import com.example.myapplication.ui.theme.KTCTheme
import com.example.myapplication.ui.theme.surfaceDim

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HmBottomScaffold(
    scaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
    onDismissRequest: () -> Unit,
    sheetTitle: String = "",
    sheetContent: @Composable ColumnScope.() -> Unit = {},
    sheetButton: @Composable ColumnScope.() -> Unit = {},
    topBar: @Composable (() -> Unit)? = null,
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContainerColor = MaterialTheme.colorScheme.surfaceDim,
        sheetShape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
        topBar = topBar,
        sheetDragHandle = null,
        sheetShadowElevation = 10.dp,
        sheetTonalElevation = 10.dp,
        sheetContent = {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surfaceDim)
                    .padding(horizontal = 16.dp, vertical = 30.dp)
            ) {
                if (sheetTitle.isNotEmpty()) {
                    Text(
                        text = sheetTitle,
                        style = KTCTheme.typography.headlineMediumB,
                    )
                }
                sheetContent()
                sheetButton()
            }
        },
        sheetSwipeEnabled = false
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            Column {
                Box(modifier = Modifier.weight(1f)) {
                    content(padding)
                }
                bottomBar()
            }
            Scrim(
                color = BottomSheetDefaults.ScrimColor,
                onDismissRequest = onDismissRequest,
                visible = scaffoldState.bottomSheetState.isVisible
            )
        }
    }
}

@Composable
private fun Scrim(
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun HmBottomScaffoldPreview() {
    HmmTheme {
        val scaffoldState = rememberBottomSheetScaffoldState(
            SheetState(true, SheetValue.Expanded, { true }, false)
        )

        HmBottomScaffold(
            scaffoldState = scaffoldState,
            sheetTitle = "화물등록",
            sheetContent = {
                Text(
                    text = "화물 등록을 하시겠습니까?",
                    style = KTCTheme.typography.titleLargeM,
                    color = DarkGray,
                    modifier = Modifier.padding(vertical = 20.dp)
                )
            },
            sheetButton = {
                HmWeightedRoundSplitButton(
                    leftText = "아니요",
                    rightText = "예",
                    onLeftClick = {  },
                    onRightClick = {  }
                )
            },
            onDismissRequest = { /*TODO*/ }
        ) {

        }
    }
}