package ktc.cargo.components.icon

import androidx.compose.ui.graphics.vector.ImageVector
import ktc.cargo.components.icon.myiconpack.Modify
import ktc.cargo.components.icon.myiconpack.Visibility
import ktc.cargo.components.icon.myiconpack.VisibilityOff
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
