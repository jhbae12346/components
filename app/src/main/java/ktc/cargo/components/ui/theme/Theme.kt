package ktc.cargo.components.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    surfaceDim = Black,
    primary = White1,
    onPrimary = BlackGray,
    inversePrimary = DarkBlue,
    primaryContainer = Graphite,
    onPrimaryContainer = BlackGray,
    secondary = White1,
    onSecondary = BlackGray,
    secondaryContainer = BlackGray,
    onSecondaryContainer = Gray6,
    tertiary = Gray5,
    onTertiary = Yellow01,
    tertiaryContainer = Yellow04,
    onTertiaryContainer = White1,
    error = Red02,
    onError = Red05,
    errorContainer = Red04,
    onErrorContainer = Red01,
    surface = DarkBlue,
    onSurface = White1,
    surfaceVariant = Gray10,
    onSurfaceVariant = Gray1,
    outline = Gray2,
    outlineVariant = Cosmos,
    scrim = Black,
)

private val LightColorScheme = lightColorScheme(
    surfaceDim = White2,
    primary = DarkBlue,
    onPrimary = White1,
    inversePrimary = White1,
    primaryContainer = White1,
    onPrimaryContainer = Black,
    secondary = Blue10,
    onSecondary = White1,
    secondaryContainer = Gray9,
    onSecondaryContainer = Gray4,
    tertiary = Gray2,
    onTertiary = Black,
    tertiaryContainer = Yellow03A40,
    onTertiaryContainer = Yellow04,
    error = Red03,
    onError = White1,
    errorContainer = Red01,
    onErrorContainer = Red06,
    surface = White1,
    onSurface = BlackGray,
    surfaceVariant = Gray3,
    onSurfaceVariant = White1,
    outline = Gray8,
    outlineVariant = Gray2,
    scrim = Black,
)

val LocalDarkTheme = compositionLocalOf { true }

@Composable
fun HmmTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = DarkBlue.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    CompositionLocalProvider(
        LocalDarkTheme provides darkTheme,
        LocalTypography provides Typography,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content
        )
    }
}

object KTCTheme {
    val typography: KTCTypography
        @Composable
        get() = LocalTypography.current
}