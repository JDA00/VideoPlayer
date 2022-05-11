package com.jda00.videoplayer.ui

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jda00.videoplayer.R

/**
Custom font styles to loosely replicate look of sample wireframe
 */


val caveatFamily = FontFamily(
    Font(R.font.caveat_semibold, weight = FontWeight.SemiBold),
    Font(R.font.caveat_regular, weight = FontWeight.Normal),
    Font(R.font.caveat_medium, weight = FontWeight.Medium),
    Font(R.font.caveat_bold, weight = FontWeight.Bold)
)

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = caveatFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),

    h1 = TextStyle(
        fontFamily = caveatFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 30.sp
    ),

    body2 = TextStyle(
        fontFamily = caveatFamily,
        fontWeight = FontWeight.Light,
        fontSize = 20.sp
    ),

    h2 = TextStyle(
        fontFamily = caveatFamily,
        fontWeight = FontWeight.Thin,
        fontSize = 20.sp
    ),
)



