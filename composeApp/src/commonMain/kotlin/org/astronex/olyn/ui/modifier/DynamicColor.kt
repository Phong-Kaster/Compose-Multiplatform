package org.astronex.olyn.ui.modifier

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import org.astronex.olyn.domain.enums.CyclePhase


@Composable
fun Modifier.dynamicCycleBackground(cyclePhase: CyclePhase) = composed {
    val targetColor by remember(cyclePhase) {
        derivedStateOf {
            cyclePhase.backgroundColor
        }
    }

    val animatedColor by animateColorAsState(
        targetValue = targetColor,
        animationSpec = tween(durationMillis = 800),
        label = "animatedColor"
    )

    this.background(
        brush = Brush.linearGradient(
            colorStops = arrayOf(
                0.0f to animatedColor,
                0.45f to Color(0xFFFFFFFF)
            )
        )
    )
}

@Composable
fun Modifier.dynamicCycleBackgroundSymptomSuggestion(cyclePhase: CyclePhase) = composed {
    val targetBackgroundColor by remember(cyclePhase) {
        derivedStateOf {
            cyclePhase.backgroundColor
        }
    }

    val backgroundColor by animateColorAsState(
        targetValue = targetBackgroundColor,
        animationSpec = tween(durationMillis = 800),
        label = "targetBackgroundColor"
    )


    this.background(
        brush = Brush.linearGradient(
            colorStops = arrayOf(
                0.0f to backgroundColor,
                0.85f to Color(0xFFFFF9F5)
            )
        )
    )
}

@Composable
fun Modifier.dynamicCycleButtonColor(cyclePhase: CyclePhase) = composed {
    val targetColor by remember(cyclePhase) {
        derivedStateOf {
            cyclePhase.buttonColor
        }
    }

    val animatedColor by animateColorAsState(
        targetValue = targetColor,
        animationSpec = tween(durationMillis = 800),
        label = "dynamicCycleButtonColor"
    )

    this.background(color = animatedColor)
}

@Composable
fun dynamicCycleTextButtonColor(cyclePhase: CyclePhase): Color {
    val targetColor by remember(cyclePhase) {
        derivedStateOf {
            cyclePhase.textButtonColor
        }
    }

    val animatedColor by animateColorAsState(
        targetValue = targetColor,
        animationSpec = tween(durationMillis = 800),
        label = "dynamicCycleTextButtonColor"
    )

    return animatedColor
}

@Composable
fun dynamicCycleThemeColor(cyclePhase: CyclePhase): Color {
    val targetColor by remember(cyclePhase) {
        derivedStateOf {
            cyclePhase.themeColor
        }
    }


    val animatedColor by animateColorAsState(
        targetValue = targetColor,
        animationSpec = tween(durationMillis = 800),
        label = "dynamicCycleThemeColor"
    )

    return animatedColor
}

