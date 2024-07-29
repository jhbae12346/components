package ktc.cargo.driver.hmmdatetimepicker.datepicker.models

import ktc.cargo.components.etc.datetimepicker.core.util.BaseConstants
import ktc.cargo.driver.hmmdatetimepicker.datepicker.views.SelectionButton

abstract class BaseSelection {
    open val withButtonView: Boolean = true
    open val extraButton: SelectionButton? = null
    open val onExtraButtonClick: (() -> Unit)? = null
    open val negativeButton: SelectionButton? = BaseConstants.DEFAULT_NEGATIVE_BUTTON
    open val onNegativeClick: (() -> Unit)? = null
    open val positiveButton: SelectionButton = BaseConstants.DEFAULT_POSITIVE_BUTTON
}