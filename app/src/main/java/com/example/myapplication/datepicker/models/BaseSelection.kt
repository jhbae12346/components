package com.example.myapplication.datepicker.models

import com.example.myapplication.datepicker.core.util.BaseConstants
import com.example.myapplication.datepicker.views.SelectionButton

abstract class BaseSelection {
    open val withButtonView: Boolean = true
    open val extraButton: SelectionButton? = null
    open val onExtraButtonClick: (() -> Unit)? = null
    open val negativeButton: SelectionButton? = BaseConstants.DEFAULT_NEGATIVE_BUTTON
    open val onNegativeClick: (() -> Unit)? = null
    open val positiveButton: SelectionButton = BaseConstants.DEFAULT_POSITIVE_BUTTON
}