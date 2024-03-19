package com.example.myapplication.datepicker.core.base

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.myapplication.component.HmBottomScaffold
import com.example.myapplication.ui.theme.surfaceDim

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun BottomSheetBase(
    scaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
    properties: DialogProperties = DialogProperties(),
    onDialogClick: (() -> Unit)? = null,
    onDisMissRequest: () -> Unit,
    content: @Composable (PaddingValues) -> Unit,
    bottomBar: @Composable () -> Unit = {},
    sheetContent: @Composable () -> Unit,
) {

    val boxInteractionSource = remember { MutableInteractionSource() }
    val contentInteractionSource = remember { MutableInteractionSource() }

    HmBottomScaffold(
        scaffoldState = scaffoldState,
        onDismissRequest = onDisMissRequest,
        sheetContent = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clickable(
                        interactionSource = boxInteractionSource,
                        indication = null,
                        onClick = {
                            if (properties.dismissOnClickOutside) onDisMissRequest()
                        }
                    )
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize()
                        .clickable(
                            indication = null,
                            interactionSource = contentInteractionSource,
                            onClick = { onDialogClick?.invoke() }
                        ),
                    color = MaterialTheme.colorScheme.surfaceDim,
                    content = sheetContent
                )
            }
        },
        bottomBar = bottomBar,
        content = content
    )
}