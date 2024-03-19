package com.example.myapplication.datepicker.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myapplication.datepicker.models.CalendarDateData
import com.example.myapplication.datepicker.models.LibOrientation
import com.example.myapplication.ui.theme.KTCTheme
import com.example.myapplication.ui.theme.Sky
import com.example.myapplication.ui.theme.White2
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * The date item component of the calendar view.
 * @param data The data for the date.
 * @param selection The selection configuration for the dialog view.
 * @param onDateClick The listener that is invoked when a date is clicked.
 */
@Composable
internal fun CalendarDateItemComponent(
    orientation: LibOrientation,
    data: CalendarDateData,
    onDateClick: (LocalDate) -> Unit = {},
) {

    val today = data.date == LocalDate.now()
    val defaultShape = when (orientation) {
        LibOrientation.PORTRAIT -> MaterialTheme.shapes.medium
        LibOrientation.LANDSCAPE -> MaterialTheme.shapes.small
    }

    val disabledModifier = Modifier
        .aspectRatio(1f, true)
        .clip(defaultShape)
        .background(MaterialTheme.colorScheme.surfaceVariant)

    val selectedModifier = Modifier
        .aspectRatio(1f, true)
        .clip(RoundedCornerShape(5.dp))
        .background(Sky)
        .clickable { data.date?.let { onDateClick(it) } }

    val normalModifier = Modifier
        .aspectRatio(1f, true)
        .clip(defaultShape)
        .clickable { data.date?.let { onDateClick(it) } }

    val todayModifier = Modifier
        .aspectRatio(1f, true)
        .border(2.dp, Sky, RoundedCornerShape(5.dp))
        .clickable { data.date?.let { onDateClick(it) } }

    val otherMonthModifier = Modifier
        .aspectRatio(1f, true)

    val textStyle =
        when {
            data.disabledPassively -> KTCTheme.typography.titleLargeM.copy(
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
            data.selectedBetween || data.selected -> KTCTheme.typography.titleLargeM.copy(White2)
            today -> KTCTheme.typography.titleLargeM.copy(Sky)
            else -> KTCTheme.typography.titleLargeM
        }

    val parentModifier = Modifier.padding(2.dp)

    val cellModifier = when {
        data.otherMonth || data.disabledPassively -> otherMonthModifier
        data.selected -> selectedModifier
        data.disabled -> disabledModifier
        today -> todayModifier
        else -> normalModifier
    }

    Column(modifier = parentModifier) {
        Row(
            modifier = cellModifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = data.date?.format(DateTimeFormatter.ofPattern("d"))
                    ?.takeUnless { data.otherMonth } ?: "",
                style = textStyle,
                textAlign = TextAlign.Center
            )
        }
    }
}