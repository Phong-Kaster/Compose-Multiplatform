package org.astronex.olyn.ui.splash.component

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer

import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import compose_multiplatform.composeapp.generated.resources.Res
import compose_multiplatform.composeapp.generated.resources.img_app_icon_rounded
import org.astronex.olyn.ui.theme.customizedTextStyle
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SplashAnimation1() {
    /** Opacity animation */
    var opacity by remember { mutableFloatStateOf(0f) }
    val opacityAnimation by animateFloatAsState(
        targetValue = opacity,
        animationSpec = tween(durationMillis = 1500)
    )

    /** Translation animation */
    var isTranslated by remember { mutableStateOf(false) }
    val translation: Float by animateFloatAsState(
        targetValue = if (isTranslated) 0f else 1f,
        animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing)
    )

    /** Scale animation */
    var isScaled by remember { mutableStateOf(false) }

    val scale: Float by animateFloatAsState(
        targetValue = if (isScaled) 1f else 0f,
        animationSpec = tween(durationMillis = 400)
    )

    LaunchedEffect(Unit) {
        isScaled = true
        delay(2100)
        isTranslated = true
        opacity = 1f
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(2 / 3f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(resource = Res.drawable.img_app_icon_rounded),
                contentDescription = null,
                modifier = Modifier
                    .size(280.dp)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        transformOrigin = TransformOrigin.Center
                    },
            )
            Text(
                text = "OLYN",
                style = customizedTextStyle(
                    fontSize = 38,
                    fontWeight = 700,
                    color = Color(0xFF974343)
                ),
                modifier = Modifier
                    .graphicsLayer {
                        alpha = opacityAnimation
                        translationY = 300 * translation
                    }
            )
        }
    }
}

@Preview
@Composable
private fun PreviewSplashAnimation1() {
    SplashAnimation1()
}