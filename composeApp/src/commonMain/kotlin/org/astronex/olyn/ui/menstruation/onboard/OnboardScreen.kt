package org.astronex.olyn.ui.menstruation.onboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.astronex.olyn.ui.core.CoreLayout
import org.astronex.olyn.ui.theme.SplashBrushBackground
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun OnboardScreen(
    onOpenHome: () -> Unit,
) {
    val onboardViewModel = koinViewModel<OnboardViewModel>()

    OnboardLayout(
        onOpenHome = {
            onboardViewModel.setEnableOnboard(boolean = false)
            onOpenHome()
        },
    )
}

@Composable
fun OnboardLayout(
    onOpenHome: () -> Unit = {}
) {
    CoreLayout(
        modifier = Modifier
            .background(brush = SplashBrushBackground),
        content = {
            Column(modifier = Modifier) {
                Text(
                    text = "Onboard Screen",
                )

                Button(
                    onClick = onOpenHome,
                    content = {
                        Text(text = "Open Home")
                    }
                )
            }

        },
    )
}

@Preview
@Composable
private fun PreviewOnboardScreen() {
    OnboardLayout()
}