package com.example.myapplication.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.Hmm_mancharoTheme
import com.example.myapplication.ui.theme.KTCTheme
import com.example.myapplication.ui.theme.surfaceDim


data class TabValue(val title: String, val content: @Composable () -> Unit)

/**
 *  rememberModalBottomSheetState(true)여야 합니다.
 * */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HmTabPopUp(
    sheetState: SheetState,
    popUpType: PopUpType = PopUpType.BottomSheet,
    content: List<TabValue>,
    leftText: String = "닫기",
    rightText: String = "선택",
    onLeftClick: () -> Unit,
    onRightClick: () -> Unit,
    onDismissRequest: () -> Unit
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    HmDefaultPopUp(
        sheetState = sheetState,
        popUpType = popUpType,
        onDismissRequest = onDismissRequest,
        header = {
            CustomScrollableTabRow(
                tabs = content.map { it.title },
                selectedTabIndex = selectedTabIndex,
            ) { tabIndex ->
                selectedTabIndex = tabIndex
            }
        },
        content = {
            Box(
                modifier = Modifier.heightIn(min = screenHeight / 2, max = screenHeight / 5 * 4)
            ) {
                content[selectedTabIndex].content()
            }
        },
        button = {
            HmWeightedRoundSplitButton(
                leftText = leftText,
                rightText = rightText,
                onLeftClick = onLeftClick,
                onRightClick = onRightClick
            )
        }
    )
}

@Composable
private fun CustomScrollableTabRow(
    tabs: List<String>,
    selectedTabIndex: Int,
    onTabClick: (Int) -> Unit
) {
    val density = LocalDensity.current
    val tabWidths = remember {
        val tabWidthStateList = mutableStateListOf<Dp>()
        repeat(tabs.size) {
            tabWidthStateList.add(0.dp)
        }
        tabWidthStateList
    }
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = MaterialTheme.colorScheme.surfaceDim,
        contentColor = MaterialTheme.colorScheme.onSurface,
        edgePadding = 0.dp,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.customTabIndicatorOffset(
                    currentTabPosition = tabPositions[selectedTabIndex],
                    tabWidth = tabWidths[selectedTabIndex]
                ),
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        divider = {}
    ) {
        tabs.forEachIndexed { tabIndex, tab ->
            Tab(
                selected = selectedTabIndex == tabIndex,
                onClick = { onTabClick(tabIndex) },
                text = {
                    Text(
                        text = tab,
                        style = if (selectedTabIndex == tabIndex) {
                            KTCTheme.typography.titleLargeB
                        } else {
                            KTCTheme.typography.titleLargeM
                        },
                        color = if (selectedTabIndex == tabIndex) {
                            MaterialTheme.colorScheme.onSurface
                        } else {
                            MaterialTheme.colorScheme.surfaceVariant
                        },
                        onTextLayout = { textLayoutResult ->
                            tabWidths[tabIndex] =
                                with(density) { textLayoutResult.size.width.toDp() }
                        }
                    )
                },
            )
        }
    }
}

private fun Modifier.customTabIndicatorOffset(
    currentTabPosition: TabPosition,
    tabWidth: Dp
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "customTabIndicatorOffset"
        value = currentTabPosition
    }
) {
    val currentTabWidth by animateDpAsState(
        targetValue = tabWidth,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing), label = ""
    )
    val indicatorOffset by animateDpAsState(
        targetValue = ((currentTabPosition.left + currentTabPosition.right - tabWidth) / 2),
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing), label = ""
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset)
        .width(currentTabWidth)
}

@OptIn(ExperimentalMaterial3Api::class)
@ThemePreviews
@Composable
fun HmTabPopUpPreview(
    @PreviewParameter(PopUpTypeProvider::class) popUpType: PopUpType
) {
    Hmm_mancharoTheme {
        HmTabPopUp(
            sheetState = SheetState(true, SheetValue.Expanded, { true }, false),
            popUpType = popUpType,
            content = listOf(
                TabValue(title = "상차일시") {
                    HmChoiceList(
                        choices = listOf(
                            ChoiceItem("전체", true),
                            ChoiceItem("일상"),
                            ChoiceItem("당상"),
                            ChoiceItem("인수증+카드"),
                            ChoiceItem("당상"),
                            ChoiceItem("당상"),
                            ChoiceItem("당상"),
                            ChoiceItem("당상"),
                            ChoiceItem("인수증+카드"),
                            ChoiceItem("인수증+카드"),
                            ChoiceItem("인수증+카드"),
                            ChoiceItem("인수증+카드"),
                            ChoiceItem("인수증+카드"),
                            ChoiceItem("인수증+카드"),
                            ChoiceItem("인수증+카드"),
                        )
                    )
                },
                TabValue(title = "하차일시") {}
            ),
            onLeftClick = {},
            onRightClick = {},
            onDismissRequest = {}
        )
    }
}