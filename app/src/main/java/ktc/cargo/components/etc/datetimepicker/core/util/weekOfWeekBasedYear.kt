package ktc.cargo.components.etc.datetimepicker.core.util

import androidx.annotation.RestrictTo
import ktc.cargo.components.etc.datetimepicker.models.CalendarConfig
import ktc.cargo.components.etc.datetimepicker.models.CalendarSelection
import ktc.cargo.driver.hmmdatetimepicker.datepicker.models.CalendarData
import ktc.cargo.driver.hmmdatetimepicker.datepicker.models.CalendarDateData
import ktc.cargo.driver.hmmdatetimepicker.datepicker.models.CalendarMonthData
import ktc.cargo.driver.hmmdatetimepicker.datepicker.core.util.Constants
import ktc.cargo.driver.hmmdatetimepicker.datepicker.models.CalendarStyle
import ktc.cargo.driver.hmmdatetimepicker.datepicker.models.CalendarViewType
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.time.temporal.WeekFields
import java.util.Locale

/**
 * Returns the week of the week-based-year for this [LocalDate].
 *
 * The week of the week-based-year is defined using the [Locale.getDefault] locale's [WeekFields].
 *
 * @return an `Int` representing the week of the week-based-year for this [LocalDate].
 */
internal val LocalDate.weekOfWeekBasedYear: Int
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    get() = get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear())

/**
 * Returns the date for the first day of the week (Monday) for this [LocalDate].
 */
internal val LocalDate.startOfWeek: LocalDate
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    get() = minusDays(dayOfWeek.value - 1L)

/**
 * Returns the date for the last day of the week (Sunday) for this [LocalDate].
 */
internal val LocalDate.endOfWeek: LocalDate
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    get() = plusDays(7L - dayOfWeek.value)

/**
 * Extension function that jumps to the first day of the same week (Monday) or the first day of the month, whichever comes first.
 *
 * @return [LocalDate] representing the first day of the week or the month, whichever is earliest
 */
internal val LocalDate.startOfWeekOrMonth: LocalDate
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    get() {
        var result = this
        while (result.dayOfMonth > 1 && result.dayOfWeek != DayOfWeek.MONDAY) {
            result = result.minusDays(1)
        }
        return result
    }

/**
 * Extension function that jumps to the first day of the month.
 *
 * @return [LocalDate] representing the first day of the month
 */
internal val LocalDate.startOfMonth: LocalDate
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    get() = withDayOfMonth(1)

/**

Extension function that jumps to the last day of the month.
@return [LocalDate] representing the last day of the month
 */
internal val LocalDate.endOfMonth: LocalDate
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    get() = with(TemporalAdjusters.lastDayOfMonth())

/**
 * Get the first day of the previous week from the current date.
 *
 * Skips in current week to previous month's Monday if day is the first day of the month and not Monday.
 * Skips to previous week if the current day of the month is greater than or equal to 7 or is the first day of the month and is Monday.
 * Skips to the first day of the previous month otherwise.
 * @return The first day of the previous week as a `LocalDate` object.
 */
internal val LocalDate.previousWeek: LocalDate
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    get() = when {
        dayOfMonth == Constants.FIRST_DAY_IN_MONTH
                && dayOfWeek != DayOfWeek.MONDAY -> with(DayOfWeek.MONDAY)

        dayOfMonth >= Constants.DAYS_IN_WEEK ||
                dayOfMonth == Constants.FIRST_DAY_IN_MONTH
                && dayOfWeek == DayOfWeek.MONDAY -> minusWeeks(1)

        else -> withDayOfMonth(Constants.FIRST_DAY_IN_MONTH)
    }


/**
 * Get the date of the next week from the current date.
 *
 * The next week is determined based on the current day of the month and the remaining days in the month.
 * If the current day of the month is the first day of the month, it skips to the next Monday.
 * If there are less than 7 days remaining in the current month, it skips to the first day of the next month.
 * @return The first day of the next week as a `LocalDate` object.
 */
internal val LocalDate.nextWeek: LocalDate
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    get() = when {
        dayOfMonth == Constants.FIRST_DAY_IN_MONTH -> plusDays((7 - dayOfWeek.value) + 1L)
        lengthOfMonth() - dayOfMonth >= Constants.DAYS_IN_WEEK -> plusWeeks(1)
        else -> plusMonths(1).withDayOfMonth(Constants.FIRST_DAY_IN_MONTH)
    }

/**
 * Returns a new `LocalDate` instance representing the previous date based on the `CalendarConfig` passed.
 *
 * If `CalendarConfig.style` is set to `CalendarStyle.MONTH`, the function returns the first day of the previous month.
 * If `CalendarConfig.style` is set to `CalendarStyle.WEEK`, the function returns the first day (Monday) of the previous week.
 *
 * @param config The `CalendarConfig` to determine the jump step.
 * @return A new `LocalDate` instance representing the previous date based on the `CalendarConfig`.
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
fun LocalDate.jumpPrev(config: CalendarConfig): LocalDate = when (config.style) {
    CalendarStyle.MONTH -> this.minusMonths(1).withDayOfMonth(1)
    CalendarStyle.WEEK -> this.previousWeek
}

/**
 * Returns a new `LocalDate` instance representing the next date based on the `CalendarConfig` passed.
 *
 * If `CalendarConfig.style` is set to `CalendarStyle.MONTH`, the function returns the first day of the next month.
 * If `CalendarConfig.style` is set to `CalendarStyle.WEEK`, the function returns the first day (Monday) of the next week.
 *
 * @param config The `CalendarConfig` to determine the jump step.
 * @return A new `LocalDate` instance representing the next date based on the `CalendarConfig`.
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
fun LocalDate.jumpNext(config: CalendarConfig): LocalDate = when (config.style) {
    CalendarStyle.MONTH -> this.plusMonths(1).withDayOfMonth(1)
    CalendarStyle.WEEK -> this.nextWeek
}

/**
 * Returns the initial date to be displayed on the CalendarView based on the selection mode.
 * @param selection The selection mode.
 * @param boundary The boundary of the calendar.
 * @return The initial date to be displayed on the CalendarView.
 */
internal fun getInitialCameraDate(
    selection: CalendarSelection,
    boundary: ClosedRange<LocalDate>
): LocalDate {
    val cameraDateBasedOnMode = when (selection) {
        is CalendarSelection.Date -> selection.selectedDate
        is CalendarSelection.Dates -> selection.selectedDates?.firstOrNull()
        is CalendarSelection.Period -> selection.selectedRange?.lower
    } ?: run {
        val now = LocalDate.now()
        if (now in boundary) now else boundary.start
    }
    return cameraDateBasedOnMode.startOfWeekOrMonth
}

/**
 * Returns the custom initial date in case the camera date is within the boundary. Otherwise, it returns null.
 *
 * @param cameraDate The initial camera date.
 * @param boundary The boundary of the calendar.
 * @return The initial camera date if it's within the boundary, otherwise null.
 */
internal fun getInitialCustomCameraDate(
    cameraDate: LocalDate?,
    boundary: ClosedRange<LocalDate>
): LocalDate? = cameraDate?.takeIf { it in boundary }?.startOfWeekOrMonth

/**
 * Get selection value of date.
 */
internal val CalendarSelection.dateValue: LocalDate?
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    get() = if (this is CalendarSelection.Date) selectedDate else null

/**
 * Get selection value of dates.
 */
internal val CalendarSelection.datesValue: Array<LocalDate>
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    get() {
        val value = if (this is CalendarSelection.Dates) selectedDates?.toMutableList()
            ?: mutableListOf() else mutableListOf()
        return value.toTypedArray()
    }

/**
 * Get selection value of range.
 */
internal val CalendarSelection.rangeValue: Array<LocalDate?>
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    get() {
        val value = if (this is CalendarSelection.Period) selectedRange else null
        return mutableListOf(value?.lower, value?.upper).toTypedArray()
    }

/**
 * Get range start value.
 */
internal val List<LocalDate?>.startValue: LocalDate?
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    get() = this.getOrNull(Constants.RANGE_START)

/**
 * Get range end value.
 */
internal val List<LocalDate?>.endValue: LocalDate?
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    get() = this.getOrNull(Constants.RANGE_END)

/**
 * Calculate the month data based on the camera date and the restrictions.
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal fun calcMonthData(
    config: CalendarConfig,
    cameraDate: LocalDate,
    today: LocalDate = LocalDate.now()
): CalendarMonthData {
    val months = Month.values().toMutableList()

    // Check that months are within the boundary
    val boundaryFilteredMonths = months.filter { month ->
        val maxDayOfMonth = month.length(cameraDate.isLeapYear)
        val startDay = minOf(config.boundary.start.dayOfMonth, maxDayOfMonth)
        val endDay = minOf(config.boundary.endInclusive.dayOfMonth, maxDayOfMonth)
        val cameraDateWithMonth = cameraDate.withMonth(month.value).withDayOfMonth(startDay)
        cameraDateWithMonth in config.boundary || cameraDateWithMonth.withDayOfMonth(endDay) in config.boundary
    }

    return CalendarMonthData(
        selected = cameraDate.month,
        thisMonth = today.month,
        disabled = months.minus(boundaryFilteredMonths.toSet()),
    )
}

/**
 * Calculate the calendar data based on the camera-date.
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal fun calcCalendarData(config: CalendarConfig, cameraDate: LocalDate): CalendarData {
    var weekCameraDate = cameraDate

    val firstDayOfWeek = WeekFields.of(config.locale).firstDayOfWeek
    val dayOfWeek = cameraDate.dayOfWeek
    val diff = (dayOfWeek.value - firstDayOfWeek.value + 7) % 7

    val offsetStart = when (config.style) {
        CalendarStyle.MONTH -> diff
        CalendarStyle.WEEK -> {
            // Calculate the difference in days to the first day of the week from the camera date
            val dayOfWeekInMonth = cameraDate.with(TemporalAdjusters.firstDayOfMonth()).dayOfWeek
            val dayDiff = (dayOfWeekInMonth.value - firstDayOfWeek.value + 7) % 7

            // Adjust weekCameraDate to the start of the week if necessary
            val adjustedWeekCameraDate =
                if (cameraDate.dayOfMonth <= Constants.DAYS_IN_WEEK && dayDiff > 0) {
                    cameraDate.minusDays((cameraDate.dayOfWeek.value - firstDayOfWeek.value + 7) % 7.toLong())
                } else {
                    weekCameraDate
                }

            // Calculate the offset based on the adjustedWeekCameraDate
            ((adjustedWeekCameraDate.dayOfWeek.value - firstDayOfWeek.value + 7) % 7).also {
                // Update weekCameraDate if it was adjusted
                weekCameraDate = adjustedWeekCameraDate
            }
        }
    }

    val days = when (config.style) {
        CalendarStyle.MONTH -> cameraDate.lengthOfMonth()
        CalendarStyle.WEEK -> DayOfWeek.values().size
    }

    val rangedDays = (1..days.plus(offsetStart)).toMutableList()
        .map { dayIndex ->
            val date = when (config.style) {
                CalendarStyle.MONTH -> cameraDate
                    .withDayOfMonth(1)
                    .plusDays(dayIndex.toLong().minus(1))
                    .minusDays(offsetStart.toLong())

                CalendarStyle.WEEK -> weekCameraDate
                    .plusDays(dayIndex.toLong().minus(1))
                    .minusDays(offsetStart.toLong())
            }
            Pair(CalendarViewType.DAY, date)
        }.drop(
            when (config.style) {
                CalendarStyle.MONTH -> offsetStart
                CalendarStyle.WEEK -> 0
            }
        ).dropLast(
            when (config.style) {
                CalendarStyle.MONTH -> 0
                CalendarStyle.WEEK -> offsetStart
            }
        ).toMutableList()

    val isMonthStartOffset = weekCameraDate.dayOfMonth <= 7
    if (isMonthStartOffset) {
        repeat(offsetStart) {
            rangedDays.add(0, Pair(CalendarViewType.DAY_START_OFFSET, LocalDate.now()))
        }
    }

    val chunkedDays: List<List<Pair<CalendarViewType, Any>>> =
        rangedDays.chunked(Constants.DAYS_IN_WEEK)

    val weekDays: List<List<Pair<CalendarViewType, Any>>> = chunkedDays.map { week ->
        if (config.displayCalendarWeeks) {
            val newWeek = week.toMutableList().apply {
                val firstDateCalendarWeek =
                    week.first { it.first == CalendarViewType.DAY }.second as LocalDate
                val formatter = DateTimeFormatter.ofPattern("w").withLocale(config.locale)
                val calendarWeek = formatter.format(firstDateCalendarWeek)
                add(0, Pair(CalendarViewType.CW, calendarWeek))
            }
            newWeek
        } else week
    }

    return CalendarData(
        offsetStart = offsetStart,
        weekCameraDate = weekCameraDate,
        cameraDate = cameraDate,
        days = weekDays,
    )
}

/**
 * Calculate the calendar date-data based on the date.
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal fun calcCalendarDateData(
    date: LocalDate,
    calendarViewData: CalendarData,
    selection: CalendarSelection,
    config: CalendarConfig,
    selectedDate: LocalDate?,
    selectedDates: List<LocalDate>?,
    selectedRange: Pair<LocalDate?, LocalDate?>
): CalendarDateData? {

    if (date.monthValue != calendarViewData.cameraDate.monthValue) return null

    var selectedStartInit = false
    var selectedEnd = false
    var selectedBetween = false
    val selected = when (selection) {
        is CalendarSelection.Date -> selectedDate == date
        is CalendarSelection.Dates -> {
            selectedDates?.contains(date) ?: false
        }

        is CalendarSelection.Period -> {
            val selectedStart = selectedRange.first == date
            selectedStartInit = selectedStart && selectedRange.second != null
            selectedEnd = selectedRange.second == date
            selectedBetween = (selectedRange.first?.let { date.isAfter(it) } ?: false)
                    && selectedRange.second?.let { date.isBefore(it) } ?: false
            selectedBetween || selectedStart || selectedEnd
        }
    }
    val outOfBoundary = date !in config.boundary
    val disabledDate = config.disabledDates?.contains(date) ?: false

    return CalendarDateData(
        date = date,
        disabled = disabledDate,
        disabledPassively = outOfBoundary,
        selected = selected,
        selectedBetween = selectedBetween,
        selectedStart = selectedStartInit,
        selectedEnd = selectedEnd,
    )
}
