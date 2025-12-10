package org.astronex.olyn.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val ShadowColor = Color(0xFFEEEEEE)

val AppointmentColor = Color(0xFFBA7DFF)

val Pink40 = Color(0xFF7D5260)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey40 = Color(0xFF625b71)
val PurpleGrey80 = Color(0xFFCCC2DC)

val Gray3 = Color(0xFFC7C7CC)

val PrimaryColor = Color(0xFFFF9988)
val PrimaryColorDarker = Color(0xFFFF7B7B)

val BorderDefault = Color(0xFFEDF1F5)
val Black90 = Color(0xFF595959)

val ColorShadow = Color(0x12000000)
val ColorTextField = Color(0xFF888888)

val ColorPrimaryBrushes = Brush.linearGradient(
    colorStops = arrayOf(
        0.1f to Color(0xFFFF9988),
        1f to Color(0xFFFFDAA7)
    )
)

val ColorBackgroundGradient = Brush.verticalGradient(
    colorStops = arrayOf(
        0.1f to Color(0xFFFFF0EC),
        0.5f to Color(0xFFFFFFFF)
    )
)

val ColorBackgroundGradient2 = Brush.linearGradient(
    colorStops = arrayOf(
        0.5f to Color(0xFFFFFBED),
        1f to Color(0xFFFFE4E4)
    )
)

val SplashBrushBackground =  Brush.verticalGradient(
    colorStops = arrayOf(
        0.01f to Color(0xFFFFCEC5),
        0.3f to Color(0xFFFFF3F2),
    )
)