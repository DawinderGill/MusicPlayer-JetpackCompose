package com.dawinder.musicplayer_jetpackcompose.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.dawinder.musicplayer_jetpackcompose.R

/**
 * Defines the typography styles for the app.
 *
 * Contains three text styles:
 * 1. `bodyLarge` which is used for large body text,
 * 2. `bodySmall` used for small body text,
 * 3. `titleLarge` used for large titles.
 *
 * All text styles use the font family 'josefin_regular'.
 * The font sizes, weights, and colors vary depending on the style.
 */
val typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.josefin_regular)),
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.josefin_regular)),
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.josefin_regular)),
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = md_theme_light_onPrimary
    )
)