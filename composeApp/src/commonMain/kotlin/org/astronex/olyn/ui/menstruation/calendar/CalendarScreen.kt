package org.astronex.olyn.ui.menstruation.calendar

import androidx.compose.foundation.background
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.astronex.olyn.ui.component.CoreBottomBar
import org.astronex.olyn.ui.component.CoreLayout
import org.astronex.olyn.ui.theme.SplashBrushBackground
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CalendarScreen(){
    CoreLayout(
        modifier = Modifier.background(brush = SplashBrushBackground),
        bottomBar = { CoreBottomBar() },
        content = {
            Text(
                text = "Calendar",
            )
        }
    )
}

@Preview
@Composable
private fun PreviewCalendarScreen(){
    CalendarScreen()
}