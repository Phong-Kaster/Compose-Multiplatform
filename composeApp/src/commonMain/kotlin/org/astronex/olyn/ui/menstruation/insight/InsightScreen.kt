package org.astronex.olyn.ui.menstruation.insight

import androidx.compose.foundation.background
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.astronex.olyn.ui.core.CoreBottomBar
import org.astronex.olyn.ui.core.CoreLayout
import org.astronex.olyn.ui.theme.SplashBrushBackground
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun InsightScreen(){
    CoreLayout(
        modifier = Modifier.background(brush = SplashBrushBackground),
        bottomBar = { CoreBottomBar() },
        content = {
            Text(
                text = "Insight",
            )
        }
    )
}

@Preview
@Composable
fun PreviewInsightScreen(){
    InsightScreen()
}