package com.example.myapplication.datepicker.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.datepicker.core.animation.AnimatedImageVector
import com.example.myapplication.datepicker.core.animation.animatedVectorResource
import com.example.myapplication.datepicker.core.animation.rememberAnimatedVectorPainter
import com.example.myapplication.datepicker.core.util.ExperimentalAnimationGraphicsApi
import com.example.myapplication.datepicker.models.CalendarConfig
import com.example.myapplication.datepicker.models.CalendarDisplayMode
import com.example.myapplication.ui.theme.KTCTheme
import com.example.myapplication.ui.theme.surfaceDim
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Top header component of the calendar dialog.
 * @param config The general configuration for the dialog.
 * @param mode The display mode of the dialog.
 * @param navigationDisabled Whenever the navigation of the navigation is disabled.
 * @param prevDisabled Whenever the navigation to the previous period is disabled.
 * @param nextDisabled Whenever the navigation to the next period is disabled.
 * @param cameraDate The current camera-date of the month view.
 * @param onPrev The listener that is invoked when the navigation to the previous period is invoked.
 * @param onNext The listener that is invoked when the navigation to the next period is invoked.
 * @param onMonthClick The listener that is invoked when the month selection was clicked.
 * @param onYearClick The listener that is invoked when the year selection was clicked.
 */
@OptIn(ExperimentalAnimationGraphicsApi::class)
@ExperimentalMaterial3Api
@Composable
internal fun CalendarTopComponent(
    modifier: Modifier,
    config: CalendarConfig,
    mode: CalendarDisplayMode,
    navigationDisabled: Boolean,
    prevDisabled: Boolean,
    nextDisabled: Boolean,
    cameraDate: LocalDate,
    onPrev: () -> Unit,
    onNext: () -> Unit,
    monthSelectionEnabled: Boolean,
    yearSelectionEnabled: Boolean,
    onMonthClick: () -> Unit,
    onYearClick: () -> Unit,
) {

    val enterTransition = expandIn(expandFrom = Alignment.Center, clip = false) + fadeIn()
    val exitTransition = shrinkOut(shrinkTowards = Alignment.Center, clip = false) + fadeOut()

    val chevronAVD = AnimatedImageVector.animatedVectorResource(R.drawable.avd_chevron_down_up)
    var chevronMonthAtEnd by remember { mutableStateOf(false) }
    var chevronYearAtEnd by remember { mutableStateOf(false) }

    LaunchedEffect(mode) {
        when (mode) {
            CalendarDisplayMode.CALENDAR -> {
                chevronMonthAtEnd = false
                chevronYearAtEnd = false
            }

            CalendarDisplayMode.MONTH -> chevronYearAtEnd = false
            CalendarDisplayMode.YEAR -> chevronMonthAtEnd = false
        }
    }

    val selectableContainerModifier = Modifier.clip(MaterialTheme.shapes.extraSmall)
    val selectableItemModifier = Modifier
        .padding(start = 8.dp)
        .padding(vertical = 4.dp)
        .padding(end = 4.dp)

    Box(modifier = modifier) {

        AnimatedVisibility(
            modifier = Modifier.align(Alignment.CenterStart),
            visible = !navigationDisabled && !prevDisabled,
            enter = enterTransition,
            exit = exitTransition
        ) {
            Surface(
                shape = RoundedCornerShape(10.dp),
                onClick = onPrev,
                enabled = !navigationDisabled && !prevDisabled,
                color = MaterialTheme.colorScheme.surfaceDim,
                shadowElevation = 4.dp,
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(
                    modifier = Modifier
                        .size(40.dp)
                        .padding(5.dp),
                    imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                    contentDescription = null
                )
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .wrapContentWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = selectableContainerModifier
                    .clickable(config.yearSelection && yearSelectionEnabled) {
                        if (config.yearSelection) {
                            chevronYearAtEnd = !chevronYearAtEnd
                        }
                        onYearClick()
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = selectableItemModifier,
                    text = cameraDate.format(DateTimeFormatter.ofPattern("yyyy")),
                    style = KTCTheme.typography.titleLargeB,
                    textAlign = TextAlign.Center
                )
                if (config.yearSelection && yearSelectionEnabled) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = rememberAnimatedVectorPainter(chevronAVD, chevronYearAtEnd),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
            Row(
                modifier = selectableContainerModifier
                    .clickable(config.monthSelection && monthSelectionEnabled) {
                        if (config.monthSelection) {
                            chevronMonthAtEnd = !chevronMonthAtEnd
                        }
                        onMonthClick()
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = selectableItemModifier,
                    text = cameraDate.format(DateTimeFormatter.ofPattern("MMM")),
                    style = KTCTheme.typography.titleLargeB,
                    textAlign = TextAlign.Center
                )
                if (config.monthSelection && monthSelectionEnabled) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = rememberAnimatedVectorPainter(chevronAVD, chevronMonthAtEnd),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

        AnimatedVisibility(
            modifier = Modifier.align(Alignment.CenterEnd),
            visible = !navigationDisabled && !nextDisabled,
            enter = enterTransition,
            exit = exitTransition
        ) {
            Surface(
                shape = RoundedCornerShape(10.dp),
                onClick = onNext,
                enabled = !navigationDisabled && !nextDisabled,
                color = MaterialTheme.colorScheme.surfaceDim,
                shadowElevation = 4.dp,
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(
                    modifier = Modifier
                        .size(40.dp)
                        .padding(5.dp),
                    imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                    contentDescription = null
                )
            }
        }
    }
}

/**
 * Top header component of the calendar dialog.
 * @param config The general configuration for the dialog.
 * @param mode The display mode of the dialog.
 * @param navigationDisabled Whenever the navigation of the navigation is disabled.
 * @param prevDisabled Whenever the navigation to the previous period is disabled.
 * @param nextDisabled Whenever the navigation to the next period is disabled.
 * @param cameraDate The current camera-date of the month view.
 * @param onPrev The listener that is invoked when the navigation to the previous period is invoked.
 * @param onNext The listener that is invoked when the navigation to the next period is invoked.
 * @param onMonthClick The listener that is invoked when the month selection was clicked.
 * @param onYearClick The listener that is invoked when the year selection was clicked.
 */
@OptIn(ExperimentalAnimationGraphicsApi::class)
@ExperimentalMaterial3Api
@Composable
internal fun CalendarTopLandscapeComponent(
    modifier: Modifier,
    config: CalendarConfig,
    mode: CalendarDisplayMode,
    navigationDisabled: Boolean,
    prevDisabled: Boolean,
    nextDisabled: Boolean,
    cameraDate: LocalDate,
    onPrev: () -> Unit,
    onNext: () -> Unit,
    onMonthClick: () -> Unit,
    onYearClick: () -> Unit,
) {

    val enterTransition = expandIn(expandFrom = Alignment.Center, clip = false) + fadeIn()
    val exitTransition = shrinkOut(shrinkTowards = Alignment.Center, clip = false) + fadeOut()

    val chevronAVD = AnimatedImageVector.animatedVectorResource(R.drawable.avd_chevron_down_up)
    var chevronMonthAtEnd by remember { mutableStateOf(false) }
    var chevronYearAtEnd by remember { mutableStateOf(false) }

    LaunchedEffect(mode) {
        when (mode) {
            CalendarDisplayMode.CALENDAR -> {
                chevronMonthAtEnd = false
                chevronYearAtEnd = false
            }

            CalendarDisplayMode.MONTH -> chevronYearAtEnd = false
            CalendarDisplayMode.YEAR -> chevronMonthAtEnd = false
        }
    }

    val selectableContainerModifier = Modifier
        .fillMaxWidth()
        .clip(MaterialTheme.shapes.extraSmall)

    val selectableItemModifier = Modifier
        .padding(start = 8.dp)
        .padding(vertical = 4.dp)
        .padding(end = 4.dp)

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
    ) {

        Row(
            modifier = selectableContainerModifier
                .clickable(config.yearSelection) {
                    if (config.yearSelection) {
                        chevronYearAtEnd = !chevronYearAtEnd
                    }
                    onYearClick()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = selectableItemModifier.weight(1f),
                text = cameraDate.format(DateTimeFormatter.ofPattern("yyyy")),
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Start
            )
            if (config.yearSelection) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = rememberAnimatedVectorPainter(chevronAVD, chevronYearAtEnd),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(Modifier.height(4.dp))

        Row(
            modifier = selectableContainerModifier
                .clickable(config.monthSelection) {
                    if (config.monthSelection) {
                        chevronMonthAtEnd = !chevronMonthAtEnd
                    }
                    onMonthClick()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = selectableItemModifier.weight(1f),
                text = cameraDate.format(DateTimeFormatter.ofPattern("MMM")),
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Start
            )
            if (config.monthSelection) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = rememberAnimatedVectorPainter(chevronAVD, chevronMonthAtEnd),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        ) {

            AnimatedVisibility(
                modifier = Modifier.align(Alignment.CenterVertically),
                visible = !navigationDisabled && !prevDisabled,
                enter = enterTransition,
                exit = exitTransition
            ) {
                Column(Modifier.align(Alignment.CenterVertically)) {
                    FilledIconButton(
                        colors = IconButtonDefaults.filledIconButtonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                        modifier = Modifier.size(32.dp),
                        enabled = !navigationDisabled && !prevDisabled,
                        onClick = onPrev
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = null
                        )
                    }

                }
            }

            AnimatedVisibility(
                modifier = Modifier.align(Alignment.CenterVertically),
                visible = !navigationDisabled && !nextDisabled,
                enter = enterTransition,
                exit = exitTransition
            ) {
                Column(Modifier.align(Alignment.CenterVertically)) {
                    FilledIconButton(
                        colors = IconButtonDefaults.filledIconButtonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                        modifier = Modifier.size(32.dp),
                        enabled = !navigationDisabled && !nextDisabled,
                        onClick = onNext
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}