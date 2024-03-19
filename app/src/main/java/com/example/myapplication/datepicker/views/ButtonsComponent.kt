package com.example.myapplication.datepicker.views

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.component.HmWeightedRoundSplitButton

@Composable
internal fun ButtonsComponent(
    onDisMissRequest: () -> Unit,
    onPositive: () -> Unit,
    onNegative: () -> Unit,
    onPositiveValid: Boolean = true,
) {
    HmWeightedRoundSplitButton(
        isActive = onPositiveValid,
        onLeftClick = {
            onNegative()
            onDisMissRequest()
        },
        onRightClick = {
            if (onPositiveValid) {
                onPositive()
                onDisMissRequest()
            }
        },
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 15.dp, bottom = 40.dp)
    )
}