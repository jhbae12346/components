package ktc.cargo.components.icon.myiconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import ktc.cargo.components.icon.MyIconPack

public val MyIconPack.VisibilityOff: ImageVector
    get() {
        if (_visibilityoff != null) {
            return _visibilityoff!!
        }
        _visibilityoff = Builder(name = "Visibilityoff", defaultWidth = 24.0.dp, defaultHeight =
                24.0.dp, viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF72757D)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(1.0f, 12.0f)
                curveTo(1.9557f, 8.3378f, 6.5178f, 4.0f, 12.0f, 4.0f)
                curveTo(17.4822f, 4.0f, 22.0433f, 8.3378f, 23.0f, 12.0f)
                curveTo(22.0443f, 15.6622f, 17.4822f, 20.0f, 12.0f, 20.0f)
                curveTo(6.5178f, 20.0f, 1.9567f, 15.6622f, 1.0f, 12.0f)
                close()
                moveTo(12.0f, 16.4444f)
                curveTo(13.3483f, 16.4444f, 14.6413f, 15.9762f, 15.5947f, 15.1427f)
                curveTo(16.5481f, 14.3092f, 17.0836f, 13.1787f, 17.0836f, 12.0f)
                curveTo(17.0836f, 10.8213f, 16.5481f, 9.6908f, 15.5947f, 8.8573f)
                curveTo(14.6413f, 8.0238f, 13.3483f, 7.5556f, 12.0f, 7.5556f)
                curveTo(10.6517f, 7.5556f, 9.3587f, 8.0238f, 8.4053f, 8.8573f)
                curveTo(7.452f, 9.6908f, 6.9163f, 10.8213f, 6.9163f, 12.0f)
                curveTo(6.9163f, 13.1787f, 7.452f, 14.3092f, 8.4053f, 15.1427f)
                curveTo(9.3587f, 15.9762f, 10.6517f, 16.4444f, 12.0f, 16.4444f)
                close()
                moveTo(12.0f, 14.6667f)
                curveTo(11.191f, 14.6667f, 10.4152f, 14.3857f, 9.8432f, 13.8856f)
                curveTo(9.2712f, 13.3855f, 8.9498f, 12.7072f, 8.9498f, 12.0f)
                curveTo(8.9498f, 11.2928f, 9.2712f, 10.6145f, 9.8432f, 10.1144f)
                curveTo(10.4152f, 9.6143f, 11.191f, 9.3333f, 12.0f, 9.3333f)
                curveTo(12.809f, 9.3333f, 13.5848f, 9.6143f, 14.1568f, 10.1144f)
                curveTo(14.7288f, 10.6145f, 15.0502f, 11.2928f, 15.0502f, 12.0f)
                curveTo(15.0502f, 12.7072f, 14.7288f, 13.3855f, 14.1568f, 13.8856f)
                curveTo(13.5848f, 14.3857f, 12.809f, 14.6667f, 12.0f, 14.6667f)
                close()
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF72757D)),
                    strokeLineWidth = 2.0f, strokeLineCap = Round, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(2.2916f, 20.2267f)
                lineTo(21.7086f, 3.7731f)
            }
        }
        .build()
        return _visibilityoff!!
    }

private var _visibilityoff: ImageVector? = null
