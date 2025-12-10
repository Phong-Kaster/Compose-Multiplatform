package org.astronex.olyn.ui.splash.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import compose_multiplatform.composeapp.generated.resources.Res
import compose_multiplatform.composeapp.generated.resources.img_splash_2_aniamtion_egg
import compose_multiplatform.composeapp.generated.resources.img_splash_2_aniamtion_pregnancy_test
import compose_multiplatform.composeapp.generated.resources.img_splash_3_aniamtion
import compose_multiplatform.composeapp.generated.resources.period_tracker
import compose_multiplatform.composeapp.generated.resources.track_smarter_not_harder
import org.astronex.olyn.ui.theme.ColorTextField
import org.astronex.olyn.ui.theme.PrimaryColor
import org.astronex.olyn.ui.theme.customizedTextStyle
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SplashAnimation3(modifier: Modifier = Modifier) {
    /** Opacity animation */
    var opacityText by remember { mutableFloatStateOf(0f) }
    val opacityAnimationText by animateFloatAsState(
        targetValue = opacityText,
        animationSpec = tween(durationMillis = 1000)
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
        targetValue = if (isTranslated1) 1f else 0f,
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
    )

    var isTranslated2 by remember { mutableStateOf(false) }
    val translation2: Float by animateFloatAsState(
        targetValue = if (isTranslated2) 1f else 0f,
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
    )

    var isTranslated3 by remember { mutableStateOf(false) }
    val translation3: Float by animateFloatAsState(
        targetValue = if (isTranslated3) 1f else 0f,
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
    )

    LaunchedEffect(Unit) {
        opacityText = 1f
        delay(3000)
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
                .padding(horizontal = 50.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
                    .height(200.dp)
            ) {
                Image(
                    painter = painterResource(Res.drawable.img_splash_3_aniamtion),
                    contentDescription = null,
                    modifier = Modifier
                        .size(160.dp)
                        .align(Alignment.Center)
                        .graphicsLayer {
                            alpha = opacityAnimation3
                            translationY = 100 * translation3
                        }
                )

                Image(
                    painter = painterResource(Res.drawable.img_splash_2_aniamtion_egg),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                        .size(50.dp)
                        .align(Alignment.BottomStart)
                        .graphicsLayer {
                            alpha = opacityAnimation2
                            translationY = 100 * translation2
                        }
                )
                Image(
                    painter = painterResource(Res.drawable.img_splash_2_aniamtion_pregnancy_test),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .align(Alignment.BottomEnd)
                        .size(80.dp)
                        .graphicsLayer {
                            alpha = opacityAnimation1
                            translationY = 100 * translation1
                        }
                )
            }

            Spacer(modifier = Modifier.height(34.dp))

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
                text = stringResource(Res.string.track_smarter_not_harder),
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
        }
    }
}

@Preview
@Composable
private fun PreviewSplashAnimation3() {
    SplashAnimation3()
}