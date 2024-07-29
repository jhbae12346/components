package ktc.cargo.components.icon.myiconpack

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ktc.cargo.components.icon.MyIconPack

public val MyIconPack.Modify: ImageVector
    get() {
        if (_modify != null) {
            return _modify!!
        }
        _modify = Builder(name = "Modify", defaultWidth = 20.0.dp, defaultHeight = 20.0.dp,
                viewportWidth = 20.0f, viewportHeight = 20.0f).apply {
            path(fill = SolidColor(Color(0xFF414550)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(15.2458f, 7.8846f)
                lineTo(16.9946f, 6.0224f)
                lineTo(16.9946f, 6.0224f)
                curveTo(17.431f, 5.5577f, 17.6493f, 5.3253f, 17.7765f, 5.0813f)
                curveTo(18.0785f, 4.5019f, 18.0785f, 3.8115f, 17.7765f, 3.2322f)
                curveTo(17.6493f, 2.9882f, 17.431f, 2.7558f, 16.9946f, 2.291f)
                lineTo(16.9946f, 2.291f)
                curveTo(16.5059f, 1.7707f, 16.2616f, 1.5105f, 16.0027f, 1.3621f)
                curveTo(15.3866f, 1.0089f, 14.6293f, 1.0089f, 14.0132f, 1.3621f)
                curveTo(13.7543f, 1.5105f, 13.51f, 1.7707f, 13.0213f, 2.291f)
                lineTo(13.0213f, 2.291f)
                lineTo(11.4898f, 3.9219f)
                curveTo(12.3826f, 5.5361f, 13.6746f, 6.9024f, 15.2458f, 7.8846f)
                close()
                moveTo(10.0628f, 5.4414f)
                lineTo(3.6569f, 12.2626f)
                curveTo(3.2597f, 12.6855f, 3.0611f, 12.897f, 2.9316f, 13.1521f)
                curveTo(2.8021f, 13.4072f, 2.7485f, 13.6923f, 2.6414f, 14.2625f)
                lineTo(2.061f, 17.3529f)
                curveTo(1.9983f, 17.6865f, 1.967f, 17.8533f, 2.0628f, 17.9468f)
                curveTo(2.1586f, 18.0403f, 2.3245f, 18.005f, 2.6565f, 17.9343f)
                lineTo(5.3707f, 17.3562f)
                curveTo(5.9785f, 17.2268f, 6.2824f, 17.1621f, 6.5488f, 17.0119f)
                curveTo(6.8152f, 16.8617f, 7.0279f, 16.6352f, 7.4533f, 16.1822f)
                lineTo(13.8543f, 9.3663f)
                curveTo(12.3217f, 8.3416f, 11.0296f, 7.0012f, 10.0628f, 5.4414f)
                close()
            }
        }
        .build()
        return _modify!!
    }

private var _modify: ImageVector? = null


@Preview(showBackground = true)
@Composable
private fun ModifyIcon() {
    Icon(imageVector = MyIconPack.Modify, contentDescription = "")
}