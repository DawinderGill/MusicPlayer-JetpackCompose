package com.dawinder.musicplayer_jetpackcompose.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.dawinder.musicplayer_jetpackcompose.R

// Set of Material typography styles to start with
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