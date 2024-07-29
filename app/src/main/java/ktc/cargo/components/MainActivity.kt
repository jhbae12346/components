package ktc.cargo.components

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.view.WindowCompat
import ktc.cargo.components.component.HmDatePicker
import ktc.cargo.components.component.HmLargeButton
import ktc.cargo.components.component.HmRegularButton
import ktc.cargo.components.component.HmTabBottomSheet
import ktc.cargo.components.component.HmTimePicker
import ktc.cargo.components.component.TabValue
import ktc.cargo.components.ui.theme.HmmTheme
import java.time.LocalDate
import java.time.LocalTime

enum class Components {
    Default, DatePicker, TimePicker, TabsBottomSheet
}

class MainActivity : ComponentActivity() {

    override fun onStart() {
        super.onStart()
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var components by remember {
                mutableStateOf(Components.Default)
            }
            var dialogVisible by remember {
                mutableStateOf(false)
            }

            HmmTheme {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                        .navigationBarsPadding()
                    ,
                ) {
                    Spacer(modifier = Modifier.height(30.dp))
                    Components.values().forEach {
                        if (it != Components.Default) {
                            Button(onClick = {
                                components = it
                                dialogVisible = true
                            }) {
                                Text(text = it.name)
                            }
                        }
                    }
                }

                if (dialogVisible) {
                    TestDialog(
                        components = components,
                        onDismissRequest = {
                            dialogVisible = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun TestDialog(
    components: Components,
    onDismissRequest: () -> Unit
) {
    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = onDismissRequest
    ) {
        when (components) {
            Components.Default -> {}
            Components.DatePicker -> DatePickerTest(onDismissRequest)
            Components.TimePicker -> TimePickerTest(onDismissRequest)
            Components.TabsBottomSheet -> TabsBottomSheetTest(onDismissRequest)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerTest(
    onDismissRequest: () -> Unit
) {
    var showTimePicker by remember { mutableStateOf(false) }
    var time by remember { mutableStateOf(LocalTime.now()) }

    Scaffold(
        bottomBar = {
            Row {
                HmLargeButton(
                    text = "취소",
                    modifier = Modifier.weight(1f),
                    isActive = false,
                    onClick = onDismissRequest
                )
                HmLargeButton(
                    text = "확인",
                    modifier = Modifier.weight(1f),
                    onClick = {
                        showTimePicker = true
                    }
                )
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Text(text = time.toString(), modifier = Modifier.align(Alignment.Center))
            if (showTimePicker) {
                HmTimePicker(
                    selectedTime = time,
                    onDismissRequest = {
                        showTimePicker = false
                    },
                    onTimeSelected = { selectedTime ->
                        time = selectedTime
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerTest(
    onDismissRequest: () -> Unit
) {
    var showDatePicker by remember { mutableStateOf(false) }
    var date by remember { mutableStateOf(LocalDate.now()) }

    Scaffold(
        bottomBar = {
            Row {
                HmLargeButton(
                    text = "취소",
                    modifier = Modifier.weight(1f),
                    isActive = false,
                    onClick = onDismissRequest
                )
                HmLargeButton(
                    text = "확인",
                    modifier = Modifier.weight(1f),
                    onClick = {
                        showDatePicker = true
                    }
                )
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Text(text = date.toString(), modifier = Modifier.align(Alignment.Center))
            if (showDatePicker) {
                HmDatePicker(
                    selectedDate = date,
                    onDismissRequest = {
                        showDatePicker = false
                    },
                    onDateSelect = { selectedDate ->
                        date = selectedDate
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabsBottomSheetTest(
    onDismissRequest: () -> Unit
) {
    var visible by remember {
        mutableStateOf(false)
    }

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            Row {
                HmLargeButton(
                    text = "취소",
                    modifier = Modifier.weight(1f),
                    isActive = false,
                    onClick = onDismissRequest
                )
                HmLargeButton(
                    text = "확인",
                    modifier = Modifier.weight(1f),
                    onClick = {
                        visible = true
                    }
                )
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            if (visible) {
                HmTabBottomSheet(
                    tabContent = listOf(
                        TabValue(title = "1") {
                            Text(text = "첫번째 탭 화면")
                        },
                        TabValue(title = "2") {
                            Text(text = "두번째 탭 화면")
                        },
                        TabValue(title = "3") {
                            Text(text = "세번째 탭 화면")
                        },
                        TabValue(title = "4") {
                            Text(text = "네번째 탭 화면")
                        },
                        TabValue(title = "5") {
                            Text(text = "다섯번째 탭 화면")
                        },
                    ),
                    sheetButton1 = {
                        HmRegularButton(
                            text = "닫기",
                            modifier = Modifier.weight(3f),
                            isActive = false,
                            onClick = {
                                visible = true
                            }
                        )
                    },
                    sheetButton2 = {
                        HmRegularButton(
                            text = "선택",
                            modifier = Modifier.weight(7f),
                            onClick = { }
                        )
                    },
                    onDismissRequest = {
                        visible = false
                    },
                    selectedTabIndex = selectedTabIndex,
                    onTabClick = {
                        selectedTabIndex = it
                    }
                )
            }
        }
    }
}
