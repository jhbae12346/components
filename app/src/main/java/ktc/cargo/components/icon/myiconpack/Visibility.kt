package ktc.cargo.components.icon.myiconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import ktc.cargo.components.icon.MyIconPack

public val MyIconPack.Visibility: ImageVector
    get() {
        if (_visibility != null) {
            return _visibility!!
        }
        _visibility = Builder(name = "Visibility", defaultWidth = 22.0.dp, defaultHeight = 16.0.dp,
                viewportWidth = 22.0f, viewportHeight = 16.0f).apply {
            path(fill = SolidColor(Color(0xFF72757D)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(0.0f, 8.0f)
                curveTo(0.9557f, 4.3378f, 5.5178f, 0.0f, 11.0f, 0.0f)
                curveTo(16.4822f, 0.0f, 21.0433f, 4.3378f, 22.0f, 8.0f)
                curveTo(21.0443f, 11.6622f, 16.4822f, 16.0f, 11.0f, 16.0f)
                curveTo(5.5178f, 16.0f, 0.9567f, 11.6622f, 0.0f, 8.0f)
                close()
                moveTo(11.0f, 12.4444f)
                curveTo(12.3483f, 12.4444f, 13.6413f, 11.9762f, 14.5947f, 11.1427f)
                curveTo(15.5481f, 10.3092f, 16.0836f, 9.1787f, 16.0836f, 8.0f)
                curveTo(16.0836f, 6.8213f, 15.5481f, 5.6908f, 14.5947f, 4.8573f)
                curveTo(13.6413f, 4.0238f, 12.3483f, 3.5556f, 11.0f, 3.5556f)
                curveTo(9.6517f, 3.5556f, 8.3587f, 4.0238f, 7.4053f, 4.8573f)
                curveTo(6.452f, 5.6908f, 5.9163f, 6.8213f, 5.9163f, 8.0f)
                curveTo(5.9163f, 9.1787f, 6.452f, 10.3092f, 7.4053f, 11.1427f)
                curveTo(8.3587f, 11.9762f, 9.6517f, 12.4444f, 11.0f, 12.4444f)
                close()
                moveTo(11.0f, 10.6667f)
                curveTo(10.191f, 10.6667f, 9.4152f, 10.3857f, 8.8432f, 9.8856f)
                curveTo(8.2712f, 9.3855f, 7.9498f, 8.7072f, 7.9498f, 8.0f)
                curveTo(7.9498f, 7.2928f, 8.2712f, 6.6145f, 8.8432f, 6.1144f)
                curveTo(9.4152f, 5.6143f, 10.191f, 5.3333f, 11.0f, 5.3333f)
                curveTo(11.809f, 5.3333f, 12.5848f, 5.6143f, 13.1568f, 6.1144f)
                curveTo(13.7288f, 6.6145f, 14.0502f, 7.2928f, 14.0502f, 8.0f)
                curveTo(14.0502f, 8.7072f, 13.7288f, 9.3855f, 13.1568f, 9.8856f)
                curveTo(12.5848f, 10.3857f, 11.809f, 10.6667f, 11.0f, 10.6667f)
                close()
            }
        }
        .build()
        return _visibility!!
    }

private var _visibility: ImageVector? = null
