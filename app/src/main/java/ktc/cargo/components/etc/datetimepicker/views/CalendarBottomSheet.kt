package ktc.cargo.components.etc.datetimepicker.views

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties
import ktc.cargo.components.etc.datetimepicker.core.base.BottomSheetBase
import ktc.cargo.components.etc.datetimepicker.models.CalendarConfig
import ktc.cargo.components.etc.datetimepicker.models.CalendarSelection

@ExperimentalMaterial3Api
@Composable
internal fun CalendarBottomSheet(
    sheetState: SheetState,
    config: CalendarConfig = CalendarConfig(),
    properties: DialogProperties = DialogProperties(),
    selection: CalendarSelection,
    onDisMissRequest: () -> Unit,
) {
    BottomSheetBase(
        sheetState = sheetState,
        properties = properties,
        onDismissRequest = onDisMissRequest,
    ) {
        CalendarView(
            config = config,
            onDisMissRequest = onDisMissRequest,
            selection = selection,
        )
    }
}