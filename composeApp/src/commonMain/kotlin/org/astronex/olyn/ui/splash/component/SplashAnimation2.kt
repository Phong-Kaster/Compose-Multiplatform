package org.astronex.olyn.ui.splash.component

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import compose_multiplatform.composeapp.generated.resources.Res
import compose_multiplatform.composeapp.generated.resources.img_splash_2_aniamtion_left
import compose_multiplatform.composeapp.generated.resources.img_splash_2_aniamtion_mid
import compose_multiplatform.composeapp.generated.resources.img_splash_2_aniamtion_right
import compose_multiplatform.composeapp.generated.resources.one_of_the_most_accurate_cycle
import compose_multiplatform.composeapp.generated.resources.period_tracker
import org.astronex.olyn.ui.theme.ColorTextField
import org.astronex.olyn.ui.theme.PrimaryColor
import org.astronex.olyn.ui.theme.customizedTextStyle
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SplashAnimation2() {
    /** Opacity animation */
    var opacityText by remember { mutableFloatStateOf(0f) }
    val opacityAnimationText by animateFloatAsState(
        targetValue = opacityText,
        animationSpec = tween(durationMillis = 1500)
    )

    var opacity1 by remember { mutableFloatStateOf(0f) }
    val opacityAnimation1 by animateFloatAsState(
        targetValue = opacity1,
        animationSpec = tween(durationMillis = 500)
    )

    var opacity2 by remember { mutableFloatStateOf(0f) }
    val opacityAnimation2 by animateFloatAsState(
        targetValue = opacity2,
        animationSpec = tween(durationMillis = 500)
    )

    var opacity3 by remember { mutableFloatStateOf(0f) }
    val opacityAnimation3 by animateFloatAsState(
        targetValue = opacity3,
        animationSpec = tween(durationMillis = 500)
    )

    /** Translation animation */
    var isTranslated1 by remember { mutableStateOf(false) }
    val translation1: Float by animateFloatAsState(
        targetValue = if (isTranslated1) 0f else 1f,
        animationSpec = tween(durationMillis = 200, easing = FastOutLinearInEasing)
    )

    var isTranslated2 by remember { mutableStateOf(false) }
    val translation2: Float by animateFloatAsState(
        targetValue = if (isTranslated2) 0f else 1f,
        animationSpec = tween(durationMillis = 200, easing = FastOutLinearInEasing)
    )

    var isTranslated3 by remember { mutableStateOf(false) }
    val translation3: Float by animateFloatAsState(
        targetValue = if (isTranslated3) 0f else 1f,
        animationSpec = tween(durationMillis = 200, easing = FastOutLinearInEasing)
    )

    LaunchedEffect(Unit) {
        opacityText = 1f
        delay(2500)
        opacity3 = 1f
        isTranslated3 = true
        delay(1500)
        opacity2 = 1f
        isTranslated2 = true
        delay(1500)
        opacity1 = 1f
        isTranslated1 = true
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(2 / 3f)
                .padding(horizontal = 49.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(Res.string.period_tracker),
                style = customizedTextStyle(
                    fontSize = 29,
                    fontWeight = 600,
                    color = PrimaryColor
                ),
                modifier = Modifier
                    .graphicsLayer {
                        alpha = opacityAnimationText
                    },
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(Res.string.one_of_the_most_accurate_cycle),
                style = customizedTextStyle(
                    fontSize = 18,
                    fontWeight = 400,
                    color = ColorTextField
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .graphicsLayer {
                        alpha = opacityAnimationText
                    }
            )

            Spacer(modifier = Modifier.height(34.dp))

            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ) {
                Image(
                    painter = painterResource(Res.drawable.img_splash_2_aniamtion_left),
                    contentDescription = null,
                    modifier = Modifier
                        .size(70.dp)
                        .graphicsLayer {
                            alpha = opacityAnimation1
                            translationY = 300 * translation1
                        }
                )
                Image(
                    painter = painterResource(Res.drawable.img_splash_2_aniamtion_mid),
                    contentDescription = null,
                    modifier = Modifier
                        .size(70.dp)
                        .graphicsLayer {
                            alpha = opacityAnimation2
                            translationY = 300 * translation2
                        }
                )
                Image(
                    painter = painterResource(Res.drawable.img_splash_2_aniamtion_right),
                    contentDescription = null,
                    modifier = Modifier
                        .size(70.dp)
                        .graphicsLayer {
                            alpha = opacityAnimation3
                            translationY = 300 * translation3
                        }
                )
            }
        }
    }
}

@Preview()
@Composable
private fun PreviewSplashAnimation2() {
    SplashAnimation2()
}