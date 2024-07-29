package ktc.cargo.components.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import ktc.cargo.components.ui.theme.HmmTheme
import ktc.cargo.components.ui.theme.KTCTheme

@Composable
fun DefaultDialog(
    title: String,
    onDismissRequest: () -> Unit,
    contentSpace: Dp = 20.dp,
    content: @Composable () -> Unit = {},
    button1: @Composable RowScope.() -> Unit = {},
    button2: (@Composable RowScope.() -> Unit)? = null,
) {
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
                Text(text = title, style = KTCTheme.typography.headlineMediumB)
                Spacer(modifier = Modifier.height(contentSpace))
                content()
                Spacer(modifier = Modifier.height(contentSpace))
                Row {
                    button1()
                    button2?.let {
                        Spacer(modifier = Modifier.width(10.dp))
                        it()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultBottomSheet(
    sheetState: SheetState = rememberModalBottomSheetState(true),
    onDismissRequest: () -> Unit,
    sheetTitle: String = "",
    sheetContentSpace: Dp = 20.dp,
    sheetContent: @Composable () -> Unit = {},
    sheetButton1: (@Composable RowScope.() -> Unit)? = null,
    sheetButton2: (@Composable RowScope.() -> Unit)? = null,
) {
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismissRequest,
        containerColor = MaterialTheme.colorScheme.onSurfaceVariant,
        dragHandle = null
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.onSurfaceVariant)
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            if (sheetTitle.isNotEmpty()) {
                Text(
                    text = sheetTitle,
                    style = KTCTheme.typography.headlineMediumB,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(sheetContentSpace))
            }
            sheetContent()
            sheetButton1?.let { button1 ->
                Spacer(modifier = Modifier.height(sheetContentSpace))
                Row {
                    button1()
                    sheetButton2?.let { button2 ->
                        Spacer(modifier = Modifier.width(10.dp))
                        button2()
                    }
                }
            }

        }
    }
}

@Preview
@Composable
private fun HmTextDialogPreview() {
    HmmTheme {
        DefaultDialog(
            title = "배차요청",
            content = {
                Text(text = "배차요청을 하시겠습니까?")
            },
            button1 = {
                HmRegularButton(
                    text = "아니요",
                    modifier = Modifier.weight(5f),
                    isActive = false
                ) {}
            },
            button2 = {
                HmRegularButton(
                    text = "예",
                    modifier = Modifier.weight(5f)
                ) {
                }
            },
            onDismissRequest = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun HmBottomScaffoldPreview() {
    HmmTheme {
        DefaultBottomSheet(
            sheetState = SheetState(true, SheetValue.Expanded, { true }, false),
            sheetTitle = "화물등록",
            sheetContent = {
                Text(
                    text = "화물 등록을 하시겠습니까?",
                    style = KTCTheme.typography.titleLargeM,
                    color = MaterialTheme.colorScheme.onSurface
                )
            },
            sheetButton1 = {
                HmRegularButton(
                    text = "아니요",
                    modifier = Modifier.weight(3f),
                    isActive = false
                ) {}
            },
            sheetButton2 = {
                HmRegularButton(
                    text = "예",
                    modifier = Modifier.weight(7f)
                ) {
                }
            },
            onDismissRequest = { /*TODO*/ }
        )
    }
}