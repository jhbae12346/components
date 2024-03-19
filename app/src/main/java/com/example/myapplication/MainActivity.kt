package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.component.HmSplitFullButton
import com.example.myapplication.datepicker.HmDatePicker
import com.example.myapplication.timepicker.HmTimePickerScaffold
import com.example.myapplication.ui.theme.Hmm_mancharoTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            Hmm_mancharoTheme {
                DatePickerTest()
//                TimePickerTest()
            }
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

    HmTimePickerScaffold(
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

data class Person(val name: String, val age: Int, val isMan: Boolean)


@Composable
fun Person() {
    val submitEnabled = remember {
        derivedStateOf {  }
    }
}

object PersonSaver {

    val saver = listSaver(
        save = { person: Person ->
            listOf(
                person.name,
                person.age,
                person.isMan,
            )
        },
        restore = {
            Person(
                it[0] as String,
                it[1] as Int,
                it[2] as Boolean,
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
}