package com.arijeng.core.presentation.designsystem

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/**
 * Created by {Bennette Molepo} on {2024/05/10}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */

val DarkColorScheme = darkColorScheme(
    primary = ArijengOrange,
    background = ArijengBlack,
    surface = ArijengDarkGray,
    secondary = ArijengWhite,
    tertiary = ArijengWhite,
    primaryContainer = ArijengOrange30,
    onPrimary = ArijengBlack,
    onBackground = ArijengWhite,
    onSurface = ArijengWhite,
    onSurfaceVariant = ArijengGray
)

@Composable
fun ArijengTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}