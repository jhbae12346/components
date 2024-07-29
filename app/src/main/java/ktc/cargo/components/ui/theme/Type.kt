package ktc.cargo.components.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import ktc.cargo.components.R

val TextUnit.nonScaledSp
    @Composable get() = (this.value / LocalDensity.current.fontScale).sp

val notoanskr = FontFamily(
    Font(R.font.notosanscjkkr_bold, FontWeight.Bold),
    Font(R.font.notosanscjkkr_medium, FontWeight.Medium),
    Font(R.font.notosanscjkkr_regular, FontWeight.Normal)
)

private val notoanskrStyle = TextStyle(
    fontFamily = notoanskr, fontWeight = FontWeight.Normal, platformStyle = PlatformTextStyle(
        includeFontPadding = false
    )
)

// Set of Material typography styles to start with
val Typography = KTCTypography(
    displayLargeR = notoanskrStyle.copy(
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp,
    ), displayMediumR = notoanskrStyle.copy(
        fontSize = 45.sp,
        lineHeight = 52.sp,
    ), displaySmallR = notoanskrStyle.copy(
        fontSize = 36.sp,
        lineHeight = 44.sp,
    ), headlineLargeR = notoanskrStyle.copy(
        fontSize = 32.sp,
        lineHeight = 40.sp,
    ), headlineMediumB = notoanskrStyle.copy(
        fontSize = 26.sp,
        lineHeight = 34.sp,
        fontWeight = FontWeight.Bold,
    ), headlineMediumM = notoanskrStyle.copy(
        fontSize = 26.sp,
        lineHeight = 34.sp,
        fontWeight = FontWeight.Medium,
    ), headlineMediumR = notoanskrStyle.copy(
        fontSize = 26.sp,
        lineHeight = 34.sp,
    ), headlineSmallB = notoanskrStyle.copy(
        fontSize = 24.sp,
        lineHeight = 32.sp,
        fontWeight = FontWeight.Bold,
    ), headlineSmallM = notoanskrStyle.copy(
        fontSize = 24.sp,
        lineHeight = 32.sp,
        fontWeight = FontWeight.Medium,
    ), headlineSmallR = notoanskrStyle.copy(
        fontSize = 24.sp,
        lineHeight = 32.sp,
    ), titleLargeB = notoanskrStyle.copy(
        fontSize = 20.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.Bold,
    ), titleLargeM = notoanskrStyle.copy(
        fontSize = 20.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.Medium,
    ), titleLargeR = notoanskrStyle.copy(
        fontSize = 20.sp,
        lineHeight = 28.sp,
    ), titleNormalB = notoanskrStyle.copy(
        fontSize = 18.sp,
        lineHeight = 26.sp,
        fontWeight = FontWeight.Bold,
    ), titleNormalM = notoanskrStyle.copy(
        fontSize = 18.sp,
        lineHeight = 26.sp,
        fontWeight = FontWeight.Medium,
    ), titleNormalR = notoanskrStyle.copy(
        fontSize = 18.sp,
        lineHeight = 26.sp,
    ), titleMediumB = notoanskrStyle.copy(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Bold,
    ), titleMediumR = notoanskrStyle.copy(
        fontSize = 16.sp,
        lineHeight = 24.sp,
    ), titleSmallB = notoanskrStyle.copy(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.25.sp,
    ), titleSmallM = notoanskrStyle.copy(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.25.sp,
    ), titleSmallM140 = notoanskrStyle.copy(
        fontSize = 14.sp,
        lineHeight = (19.6).sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = (-0.2).sp,
    ), titleSmallR140 = notoanskrStyle.copy(
        fontSize = 14.sp,
        lineHeight = (19.6).sp,
        letterSpacing = (-0.2).sp,
    ), titleSmallR = notoanskrStyle.copy(
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ), labelLargeM = notoanskrStyle.copy(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Medium,
    ), labelMediumR = notoanskrStyle.copy(
        fontSize = 12.sp,
        lineHeight = 16.sp,
    ), labelSmallM = notoanskrStyle.copy(
        fontSize = 11.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = (-0.2).sp,
    ), bodyLargeR = notoanskrStyle.copy(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
    ), bodyMediumR = notoanskrStyle.copy(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp,
    ), bodySmallR = notoanskrStyle.copy(
        fontSize = 12.sp,
        lineHeight = 16.sp,
    )
)

@Immutable
data class KTCTypography(
    val displayLargeR: TextStyle,
    val displayMediumR: TextStyle,
    val displaySmallR: TextStyle,

    val headlineLargeR: TextStyle,
    val headlineMediumB: TextStyle,
    val headlineMediumM: TextStyle,
    val headlineMediumR: TextStyle,
    val headlineSmallB: TextStyle,
    val headlineSmallM: TextStyle,
    val headlineSmallR: TextStyle,

    val titleLargeB: TextStyle,
    val titleLargeM: TextStyle,
    val titleLargeR: TextStyle,
    val titleNormalB: TextStyle,
    val titleNormalM: TextStyle,
    val titleNormalR: TextStyle,
    val titleMediumB: TextStyle,
    val titleMediumR: TextStyle,
    val titleSmallB: TextStyle,
    val titleSmallM: TextStyle,
    val titleSmallM140: TextStyle,
    val titleSmallR: TextStyle,
    val titleSmallR140: TextStyle,

    val labelLargeM: TextStyle,
    val labelMediumR: TextStyle,
    val labelSmallM: TextStyle,

    val bodyLargeR: TextStyle,
    val bodyMediumR: TextStyle,
    val bodySmallR: TextStyle,
)

val LocalTypography = staticCompositionLocalOf {
    KTCTypography(
        labelSmallM = notoanskrStyle,
        displayLargeR = notoanskrStyle,
        displayMediumR = notoanskrStyle,
        displaySmallR = notoanskrStyle,
        headlineLargeR = notoanskrStyle,
        headlineMediumB = notoanskrStyle,
        headlineMediumM = notoanskrStyle,
        headlineMediumR = notoanskrStyle,
        headlineSmallB = notoanskrStyle,
        headlineSmallM = notoanskrStyle,
        headlineSmallR = notoanskrStyle,
        titleLargeB = notoanskrStyle,
        titleLargeM = notoanskrStyle,
        titleLargeR = notoanskrStyle,
        titleNormalB = notoanskrStyle,
        titleNormalM = notoanskrStyle,
        titleNormalR = notoanskrStyle,
        titleMediumB = notoanskrStyle,
        titleMediumR = notoanskrStyle,
        titleSmallB = notoanskrStyle,
        titleSmallM = notoanskrStyle,
        titleSmallM140 = notoanskrStyle,
        titleSmallR = notoanskrStyle,
        titleSmallR140 = notoanskrStyle,
        labelLargeM = notoanskrStyle,
        labelMediumR = notoanskrStyle,
        bodyLargeR = notoanskrStyle,
        bodyMediumR = notoanskrStyle,
        bodySmallR = notoanskrStyle,
    )
}
