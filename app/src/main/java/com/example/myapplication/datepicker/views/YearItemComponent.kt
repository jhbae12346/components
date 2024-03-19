package com.example.myapplication.datepicker.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.KTCTheme
import com.example.myapplication.ui.theme.Sky
import com.example.myapplication.ui.theme.White2

/**
 * The item component of the year selection view.
 * @param year The year that this item represents.
 * @param thisYear The current year.
 * @param selected If the year is selected.
 * @param onYearClick The listener that is invoked when a year is selected.
 */
@Composable
internal fun YearItemComponent(
    year: Int,
    thisYear: Boolean,
    selected: Boolean,
    onYearClick: (Int) -> Unit
) {
    val textStyle =
        when {
            selected -> KTCTheme.typography.titleLargeM.copy(White2)
            thisYear -> KTCTheme.typography.titleLargeM.copy(MaterialTheme.colorScheme.primary)
            else -> KTCTheme.typography.titleLargeM
        }

    val baseModifier = Modifier
        .wrapContentWidth()
        .clip(RoundedCornerShape(5.dp))
        .clickable { onYearClick(year) }

    val selectedModifier = baseModifier
        .background(Sky)

    Column(
        modifier = if (selected) selectedModifier else baseModifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(vertical = 8.dp),
            text = year.toString(),
            style = textStyle,
            textAlign = TextAlign.Center
        )
    }
}