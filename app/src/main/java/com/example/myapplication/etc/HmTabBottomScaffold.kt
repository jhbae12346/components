package com.example.myapplication.etc

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.component.HmBottomScaffold
import com.example.myapplication.component.HmRegularButton
import com.example.myapplication.component.HmScrollableTab
import com.example.myapplication.ui.theme.HmmTheme

@Immutable
data class TabValue(val title: String, val content: @Composable () -> Unit)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HmTabBottomScaffold(
    scaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
    tabContent: List<TabValue>,
    sheetButton: @Composable ColumnScope.() -> Unit = {},
    onDismissRequest: () -> Unit,
    selectedTabIndex: Int,
    onTabClick: (Int) -> Unit,
    topBar: @Composable (() -> Unit)? = null,
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    HmBottomScaffold(
        scaffoldState = scaffoldState,
        onDismissRequest = onDismissRequest,
        sheetContent = {
            Column(
                modifier = Modifier
                    .heightIn(max = screenHeight / 5 * 4)
                    .animateContentSize()
            ) {
                HmScrollableTab(
                    tabs = tabContent.map { it.title },
                    selectedTabIndex = selectedTabIndex,
                    onTabClick = onTabClick
                )
                Spacer(modifier = Modifier.height(20.dp))
                Crossfade(targetState = selectedTabIndex, label = "") { targetIndex ->
                    AnimatedContent(targetIndex, label = "") { currentTabIndex ->
                        tabContent[currentTabIndex].content()
                    }
                }
            }
        },
        sheetButton = sheetButton,
        topBar = topBar,
        bottomBar = bottomBar,
        content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun HmTabBottomScaffoldPreview() {
    HmmTheme {
        val scaffoldState = rememberBottomSheetScaffoldState(
            SheetState(true, SheetValue.Expanded, { true }, false)
        )
        var selectedTabIndex by remember { mutableIntStateOf(0) }

        HmTabBottomScaffold(
            scaffoldState = scaffoldState,
            tabContent = listOf(
                TabValue(title = "1") {},
                TabValue(title = "2") {},
                TabValue(title = "3") {},
                TabValue(title = "4") {},
                TabValue(title = "5") {},
            ),
            sheetButton = {
                Row {
                    HmRegularButton(
                        text = "닫기",
                        modifier = Modifier.weight(3f),
                        isActive = false,
                        onClick = { }
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    HmRegularButton(
                        text = "선택",
                        modifier = Modifier.weight(7f),
                        onClick = { }
                    )
                }
            },
            onDismissRequest = {},
            selectedTabIndex = selectedTabIndex,
            onTabClick = {
                selectedTabIndex = it
            }
        ) {
        }
    }
}