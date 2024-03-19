package com.example.myapplication.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.myapplication.timepicker.Scrim
import com.example.myapplication.ui.theme.Hmm_mancharoTheme
import com.example.myapplication.ui.theme.KTCTheme
import com.example.myapplication.ui.theme.surfaceDim

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HmDefaultPopUp(
    sheetState: SheetState = rememberModalBottomSheetState(),
    popUpType: PopUpType = PopUpType.BottomSheet,
    header: @Composable () -> Unit = {},
    content: @Composable () -> Unit = {},
    button: @Composable () -> Unit = {},
    onDismissRequest: () -> Unit
) {
    when (popUpType) {
        PopUpType.Dialog -> {
            Dialog(
                onDismissRequest = onDismissRequest,
            ) {
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.surfaceDim)
                            .padding(horizontal = 16.dp, vertical = 30.dp)
                    ) {
                        header()
                        Box(
                            modifier = Modifier.padding(top = 20.dp, bottom = 30.dp)
                        ) {
                            content()
                        }
                        button()
                    }
                }
            }
        }

        PopUpType.BottomSheet -> {
            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = onDismissRequest,
                dragHandle = {},
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surfaceDim)
                        .padding(horizontal = 16.dp, vertical = 30.dp)
                ) {
                    header()
                    Box(
                        modifier = Modifier.padding(top = 20.dp, bottom = 30.dp)
                    ) {
                        content()
                    }
                    button()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HmBottomScaffold(
    scaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
    onDismissRequest: () -> Unit,
    sheetContent: @Composable ColumnScope.() -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetShape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
        sheetDragHandle = null,
        sheetShadowElevation = 10.dp,
        sheetTonalElevation = 10.dp,
        sheetContent = sheetContent,
        sheetSwipeEnabled = false
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            Column {
                Box(modifier = Modifier.weight(1f)) {
                    content(padding)
                }
                bottomBar()
            }
            Scrim(
                color = BottomSheetDefaults.ScrimColor,
                onDismissRequest = onDismissRequest,
                visible = scaffoldState.bottomSheetState.isVisible
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HmTitlePopUp(
    sheetState: SheetState = rememberModalBottomSheetState(),
    popUpType: PopUpType,
    title: String,
    content: @Composable () -> Unit,
    button: @Composable () -> Unit,
    onDismissRequest: () -> Unit
) {
    HmDefaultPopUp(
        sheetState = sheetState,
        popUpType = popUpType,
        onDismissRequest = onDismissRequest,
        header = {
            Text(
                text = title,
                style = KTCTheme.typography.headlineSmallB
            )
        },
        button = button,
        content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HmTextPopUp(
    sheetState: SheetState = rememberModalBottomSheetState(),
    popUpType: PopUpType,
    title: String,
    content: String,
    button: @Composable () -> Unit,
    onDismissRequest: () -> Unit
) {
    HmDefaultPopUp(
        sheetState = sheetState,
        popUpType = popUpType,
        onDismissRequest = onDismissRequest,
        header = {
            Text(
                text = title,
                style = KTCTheme.typography.headlineSmallB,
            )
        },
        button = button,
        content = {
            Text(
                text = content,
                style = KTCTheme.typography.titleLargeM
            )
        }
    )
}

enum class PopUpType {
    Dialog, BottomSheet
}

class PopUpTypeProvider : PreviewParameterProvider<PopUpType> {
    override val values = sequenceOf(
        PopUpType.Dialog,
        PopUpType.BottomSheet
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@ThemePreviews
@Composable
fun HmTitlePopUpPreview(
    @PreviewParameter(PopUpTypeProvider::class) popUpType: PopUpType
) {
    Hmm_mancharoTheme {
        HmTitlePopUp(
            sheetState = SheetState(false, SheetValue.Expanded, { true }, false),
            popUpType = popUpType,
            title = "계산서 구분 선택",
            content = {
                HmChoiceList(choices = listOf(
                    ChoiceItem("전체", true),
                    ChoiceItem("일상"),
                    ChoiceItem("당상"),
                ))
            },
            button = {
                HmWeightedRoundSplitButton(
                    onLeftClick = {},
                    onRightClick = {}
                )
            }
        ) {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@ThemePreviews
@Composable
fun HmTextPopUpPreview(
    @PreviewParameter(PopUpTypeProvider::class) popUpType: PopUpType
) {
    Hmm_mancharoTheme {
        HmTextPopUp(
            sheetState = SheetState(false, SheetValue.Expanded, { true }, false),
            popUpType = popUpType,
            title = "휴대폰 정보가 맞나요?",
            content = "지금 쓰고 있는 휴대폰 정보와 입력한 정보가 일치하지 않습니다.",
            button = {
                HmHorizontalRoundSplitButton(
                    onLeftClick = {},
                    onRightClick = {}
                )
            }
        ) {

        }
    }
}
