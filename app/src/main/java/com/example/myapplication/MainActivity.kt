package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.myapplication.component.ChoiceItem
import com.example.myapplication.component.HmChoiceList
import com.example.myapplication.component.HmSplitFullButton
import com.example.myapplication.component.HmTabBottomScaffold
import com.example.myapplication.component.textfield.HmTextField
import com.example.myapplication.component.HmTopAppBar
import com.example.myapplication.component.TabValue
import com.example.myapplication.component.TopAppBarNavigationType
import com.example.myapplication.component.textfield.HmPasswordTextField
import com.example.myapplication.datepicker.HmDatePicker
import com.example.myapplication.timepicker.HmTimePicker
import com.example.myapplication.ui.theme.HmmTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HmmTheme {
                TabBottomSheetTest()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabBottomSheetTest() {
    val bottomSheetState = rememberModalBottomSheetState(true)
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState
    )
    val scope = rememberCoroutineScope()
    var text by remember {
        mutableStateOf("")
    }

    HmTabBottomScaffold(
        scaffoldState = scaffoldState,
        tabContent = listOf(
            TabValue(title = "지불방식") {
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
                        ChoiceItem("인수증+카드adas"),
                        ChoiceItem("인수증+카드"),
                        ChoiceItem("인수증+카드"),
                        ChoiceItem("인수증+카드"),
                        ChoiceItem("인수증+카드"),
                    )
                )
            },
            TabValue(title = "차종") {
                HmChoiceList(
                    choices = listOf(
                        ChoiceItem("전체", true),
                        ChoiceItem("카고"),
                        ChoiceItem("윙바디"),
                        ChoiceItem("카고/윙바디"),
                        ChoiceItem("추레라"),
                        ChoiceItem("탑"),
                        ChoiceItem("다마스"),
                    )
                )
            }
        ),
        onLeftClick = {},
        onRightClick = {},
        onDismissRequest = {
            scope.launch {
                scaffoldState.bottomSheetState.hide()
            }
        },
        topBar = {
            HmTopAppBar(
                title = "선택하기",
                navigationType = TopAppBarNavigationType.TextButton("새로고침") {}
            )
        },
        bottomBar = {
            HmSplitFullButton(
                onLeftClick = {
                    scope.launch {
                        scaffoldState.bottomSheetState.hide()
                    }
                },
                onRightClick = {
                    scope.launch {
                        scaffoldState.bottomSheetState.show()
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            HmPasswordTextField(
                value = text,
                onValueChange = {
                    text = it
                },
                hint = "인증번호 입력",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerTest() {
    val context = LocalContext.current
    val bottomSheetState = rememberModalBottomSheetState(true)
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState
    )
    val scope = rememberCoroutineScope()

    HmDatePicker(
        scaffoldState = scaffoldState,
        onDateSelect = {
            Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
        },
        onDisMissRequest = {
            scope.launch {
                scaffoldState.bottomSheetState.hide()
            }
        },
        bottomBar = {
            HmSplitFullButton(
                onLeftClick = {
                    scope.launch {
                        scaffoldState.bottomSheetState.hide()
                    }
                },
                onRightClick = {
                    scope.launch {
                        scaffoldState.bottomSheetState.show()
                    }
                }
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = "테스트", Modifier.align(Alignment.Center))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerTest() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(true)
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState
    )

    HmTimePicker(
        scaffoldState = scaffoldState,
        onDismissRequest = {
            scope.launch {
                scaffoldState.bottomSheetState.hide()
            }
        },
        onTimeSelected = {
            Toast.makeText(context, "$it", Toast.LENGTH_LONG).show()
        },
        bottomBar = {
            HmSplitFullButton(
                onLeftClick = {
                    scope.launch {
                        bottomSheetState.hide()
                    }
                }) {
                scope.launch {
                    bottomSheetState.expand()
                }
            }
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "테스트", Modifier.align(Alignment.Center))
        }
    }
}