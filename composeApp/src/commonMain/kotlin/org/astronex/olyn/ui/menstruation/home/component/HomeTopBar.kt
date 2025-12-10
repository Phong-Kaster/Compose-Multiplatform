package org.astronex.olyn.ui.menstruation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import compose_multiplatform.composeapp.generated.resources.Res
import compose_multiplatform.composeapp.generated.resources.ic_blood
import compose_multiplatform.composeapp.generated.resources.ic_switch_mode_2
import org.astronex.olyn.ui.theme.customizedTextStyle
import org.astronex.olyn.util.dynamicStatusBarPadding
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeTopBar(
    title: String = "",
    onOpenPregnancy: () -> Unit = {},
    onOpenNotification: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Transparent)
            .padding(16.dp)
            .statusBarsPadding()
    ) {
        // button switch mode
        Row(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(12.dp))
                .clickable { onOpenPregnancy() }
                .align(Alignment.CenterStart)
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_blood),
                contentDescription = null,
                tint = Color(0xFFFF7B7B),
                modifier = Modifier
                    .size(24.dp)
            )

            Spacer(modifier = Modifier.size(4.dp))

            Icon(
                painter = painterResource(Res.drawable.ic_switch_mode_2),
                contentDescription = null,
                tint = Color(0xFFFF7B7B),
                modifier = Modifier
                    .size(24.dp)
            )
        }

        // button open calendar picker

        Text(
            text = title,
            style = customizedTextStyle(
                fontSize = 16,
                fontWeight = 500,
                color = Color(0xFF111111),
            ),
            modifier = Modifier
                .align(Alignment.Center)
        )

    }
}

@Preview
@Composable
private fun PreviewHomeTopBar() {
    HomeTopBar(
        title = "01 Jan",
        modifier = Modifier.background(color = Color.White)
    )
}