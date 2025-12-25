package org.astronex.olyn.ui.splash

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import compose_multiplatform.composeapp.generated.resources.Res
import compose_multiplatform.composeapp.generated.resources.this_action_can_contain_ads
import org.astronex.olyn.ui.component.CoreLayout
import org.astronex.olyn.ui.splash.component.GradientProgressIndicator
import org.astronex.olyn.ui.splash.component.SplashAnimation1
import org.astronex.olyn.ui.splash.component.SplashAnimation2
import org.astronex.olyn.ui.splash.component.SplashAnimation3
import org.astronex.olyn.ui.theme.SplashBrushBackground
import org.astronex.olyn.ui.theme.customizedTextStyle
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SplashScreen(
    goHome: () -> Unit = {},
    goOnboard: () -> Unit = {},
) {
    // View Model
    val splashViewModel = koinViewModel<SplashViewModel>()

    // Enable Onboard
    val enableOnboard = splashViewModel.enableOnboard.collectAsState().value
    val enableOnboardValue by rememberUpdatedState(enableOnboard) // always use latest value

    SplashLayout(
        onOpenNextScreen = {
            if (enableOnboardValue) {
                goOnboard()
            } else {
                goHome()
            }
        }
    )
}

@Composable
private fun SplashLayout(
    onOpenNextScreen: () -> Unit,
) {
    var showAnimationSplash by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        delay(3000)
        showAnimationSplash = 1
        delay(7000)
        showAnimationSplash = 2
    }

    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        scope.launch {
            for (index in 1..100) {
                if (index == 100) {
                    onOpenNextScreen()
                    break
                }
                delay(50)
            }
        }
    }

    CoreLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = SplashBrushBackground
            ),
        bottomBar = {
            Column {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(
                        space = 0.dp,
                        alignment = Alignment.CenterVertically
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                ) {
                    GradientProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .height(6.dp)
                    )

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(16.dp)
                    )

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(Res.string.this_action_can_contain_ads),
                        textAlign = TextAlign.Center,
                        style = customizedTextStyle(
                            fontSize = 14,
                            fontWeight = 500,
                            color = Color.Black,
                        ),
                    )
                }
            }

        },
        content = {
            AnimatedContent(
                targetState = showAnimationSplash,
                transitionSpec = {
                    fadeIn(
                        animationSpec = tween(2500)
                    ) togetherWith fadeOut(animationSpec = tween(2500))
                }
            ) { targetState ->
                when (targetState) {
                    0 -> SplashAnimation1()
                    1 -> SplashAnimation2()
                    2 -> SplashAnimation3()
                }
            }

//            Box(
//                contentAlignment = Alignment.Center,
//                modifier = Modifier
//                    .fillMaxSize(),
//            ) {
//                Column(
//                    modifier = Modifier
//                        .fillMaxHeight(2 / 3f),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.img_app_icon_rounded),
//                        contentDescription = null,
//                        modifier = Modifier.size(280.dp),
//                    )
//                    Text(
//                        text = "OLYN",
//                        style = customizedTextStyle(
//                            fontSize = 38,
//                            fontWeight = 700,
//                            color = Color(0xFF974343)
//                        )
//                    )
//                }
//            }
        }
    )
}

@Preview
@Composable
private fun PreviewSplashLayout2() {
    SplashLayout(
        onOpenNextScreen = { },
    )
}

//@Preview
//@Composable
//private fun PreviewSplashLayout() {
//    KoinApplicationPreview(application = { modules(appModule()) }) {
//        SplashLayout(
//            onOpenNextScreen = { },
//        )
//    }
//}