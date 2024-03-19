package com.example.myapplication.ui.theme

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
    primary = White,
    onPrimary = Black,
    primaryContainer = Graphite,
    onPrimaryContainer = White,
    inversePrimary = Black,
    secondary = Green04,
    onSecondary = Green01,
    secondaryContainer = BlackGray,
    onSecondaryContainer = Gray,
    tertiary = UnGray,
    onTertiary = Yellow01,
    tertiaryContainer = Yellow04,
    onTertiaryContainer = White,
    error = Red02,
    onError = Red05,
    errorContainer = Red04,
    onErrorContainer = Red01,
    surface = DarkBlue,
    onSurface = White,
    surfaceVariant = PaperGray,
    outline = DarkGray,
    outlineVariant = Cosmos,
    scrim = Black,
)

private val LightColorScheme = lightColorScheme(
    primary = Black,
    onPrimary = White,
    primaryContainer = White,
    onPrimaryContainer = Black,
    inversePrimary = White,
    secondary = Sky,
    onSecondary = White,
    secondaryContainer = WhiteGray,
    onSecondaryContainer = DuskGray,
    tertiary = DarkGray,
    onTertiary = Black,
    tertiaryContainer = Yellow03A40,
    onTertiaryContainer = Yellow04,
    error = Red03,
    onError = White,
    errorContainer = Red01,
    onErrorContainer = Red06,
    surface = White,
    onSurface = BlackGray,
    surfaceVariant = LabelGray,
    outline = LightGray,
    outlineVariant = DarkGray,
    scrim = Black,
)

val LocalDarkTheme = compositionLocalOf { true }

val ColorScheme.surfaceDim
    @Composable
    get() = if (LocalDarkTheme.current) Black else White2

@Composable
fun Hmm_mancharoTheme(
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