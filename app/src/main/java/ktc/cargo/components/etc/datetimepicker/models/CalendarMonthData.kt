package ktc.cargo.driver.hmmdatetimepicker.datepicker.models

import java.time.Month

/**
 * Defines information for the months selection.
 * @param disabled the months that are displayed.
 * @param selected the month that is selected.
 * @param thisMonth the current month.
 */
internal data class CalendarMonthData(
    val selected: Month,
    val disabled: List<Month>,
    val thisMonth: Month,
)