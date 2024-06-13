package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.myapplication.component.HmFilterGroup
import com.example.myapplication.etc.ComponentItem.Companion.toComponentItems
import com.example.myapplication.ui.theme.HmmTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var list by remember {
                mutableStateOf(listOf("전체", "미지원", "지원완료").toComponentItems())
            }
            HmmTheme {
                HmFilterGroup(
                    filterList = list
                ) {

                }
            }
        }
    }
}