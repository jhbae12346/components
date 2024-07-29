package ktc.cargo.components.etc.datetimepicker.models

import ktc.cargo.components.etc.datetimepicker.core.util.BaseConstants
import ktc.cargo.driver.hmmdatetimepicker.datepicker.core.util.Constants
import ktc.cargo.driver.hmmdatetimepicker.datepicker.models.CalendarStyle
import java.time.LocalDate
import java.util.Locale

class CalendarConfig(
    val locale: Locale = Locale.getDefault(),
    val style: CalendarStyle = CalendarStyle.MONTH,
    val cameraDate: LocalDate? = null,
    val displayCalendarWeeks: Boolean = Constants.DEFAULT_DISPLAY_CALENDAR_WEEKS,
    val monthSelection: Boolean = Constants.DEFAULT_MONTH_SELECTION,
    val yearSelection: Boolean = Constants.DEFAULT_YEAR_SELECTION,
    val boundary: ClosedRange<LocalDate> = Constants.DEFAULT_RANGE,
    val disabledDates: List<LocalDate>? = null,
) : BaseConfigs()

abstract class BaseConfigs(
    open val orientation: LibOrientation? = BaseConstants.DEFAULT_LIB_LAYOUT,
)

enum class LibOrientation {
    PORTRAIT,
    LANDSCAPE,
}