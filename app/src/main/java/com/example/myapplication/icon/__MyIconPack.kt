package com.example.myapplication.icon

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.myapplication.icon.myiconpack.Modify
import com.example.myapplication.icon.myiconpack.Visibility
import com.example.myapplication.icon.myiconpack.VisibilityOff
import kotlin.collections.List as ____KtList

public object MyIconPack

private var __AllIcons: ____KtList<ImageVector>? = null

public val MyIconPack.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons= listOf(Visibility, VisibilityOff, Modify)
    return __AllIcons!!
  }
