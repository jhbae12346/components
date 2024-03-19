package com.example.myapplication.datepicker.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.example.myapplication.datepicker.core.util.BaseConstants
import com.example.myapplication.datepicker.models.CalendarDisplayMode
import com.example.myapplication.datepicker.models.LibOrientation
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.SnapOffsets
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior

/**
 * The main component for the selection of the use-case as well as the selection of month and year within the dialog.
 * @param modifier The modifier that is applied to this component.
 * @param yearListState The state of the year list selection view.
 * @param cells The amount of cells / columns that are used for the calendar grid view.
 * @param mode The display mode of the dialog.
 * @param onCalendarView The content that will be displayed if the [CalendarDisplayMode] is in [CalendarDisplayMode.CALENDAR].
 * @param onMonthView The content that will be displayed if the [CalendarDisplayMode] is in [CalendarDisplayMode.MONTH].
 * @param onYearView The content that will be displayed if the [CalendarDisplayMode] is in [CalendarDisplayMode.YEAR].
 */
@OptIn(ExperimentalSnapperApi::class)
@Composable
internal fun CalendarBaseSelectionComponent(
    modifier: Modifier,
    orientation: LibOrientation,
    yearListState: LazyListState,
    cells: Int,
    mode: CalendarDisplayMode,
    onCalendarView: LazyGridScope.() -> Unit,
    onMonthView: LazyGridScope.() -> Unit,
    onYearView: LazyListScope.() -> Unit
) {

    val baseModifier = modifier.then(
            when (orientation) {
                LibOrientation.PORTRAIT -> Modifier.padding(top = 16.dp)
                LibOrientation.LANDSCAPE -> Modifier.sizeIn(
                    maxHeight = BaseConstants.DYNAMIC_SIZE_MAX,
                    maxWidth = BaseConstants.DYNAMIC_SIZE_MAX
                )
            }
        )


    val selectionModifier = modifier
        .wrapContentHeight()
        .then(
            when (orientation) {
                LibOrientation.PORTRAIT -> Modifier.padding(top = 24.dp)
                LibOrientation.LANDSCAPE -> Modifier
            }
        )

    val baseViewModifier = Modifier
        .padding(top = 16.dp)

    val gridYearModifier = baseViewModifier
        .graphicsLayer { alpha = 0.99F }
        .drawWithContent {
            drawContent()
        }

    val behavior = rememberSnapperFlingBehavior(
        lazyListState = yearListState,
        snapOffsetForItem = SnapOffsets.Center,
    )

    when (mode) {
        CalendarDisplayMode.CALENDAR -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(cells),
                modifier = baseModifier,
                userScrollEnabled = false,
            ) {
                onCalendarView()
            }
        }
        CalendarDisplayMode.MONTH -> {
            Column(
                modifier = selectionModifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyVerticalGrid(
                    modifier = baseViewModifier,
                    columns = when (orientation) {
                        LibOrientation.PORTRAIT -> GridCells.Fixed(cells)
                        LibOrientation.LANDSCAPE -> GridCells.Adaptive(48.dp)
                    },
                    content = onMonthView
                )
                Spacer(modifier = Modifier.height(50.dp))
            }
        }
        CalendarDisplayMode.YEAR -> {
            Column(
                modifier = selectionModifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyRow(
                    modifier = gridYearModifier,
                    state = yearListState,
                    flingBehavior = behavior,
                    contentPadding = PaddingValues(horizontal = 32.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    content = onYearView
                )
                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    }
}