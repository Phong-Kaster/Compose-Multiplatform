package org.astronex.olyn.ui.component


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import compose_multiplatform.composeapp.generated.resources.log_symptoms
import org.astronex.olyn.ui.theme.ColorPrimaryBrushes
import org.astronex.olyn.ui.theme.customizedTextStyle
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ButtonBottomBar(
    modifier: Modifier = Modifier,
    titleText: StringResource,
    leftIcon: DrawableResource? = null,
    onClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(ColorPrimaryBrushes)
            .clickable { onClick() }
            .padding(vertical = 15.dp),
        contentAlignment = Alignment.Center
    ) {
        Row (
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            if (leftIcon != null) {
                Icon(
                    painter = painterResource(leftIcon),
                    contentDescription = "Icon",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
            Text(
                text = stringResource(titleText),
                style = customizedTextStyle(
                    fontSize = 16,
                    fontWeight = 500,
                    color = Color.White
                ),
            )
        }
    }
}

@Preview
@Composable
private fun ButtonBottomBarPreview() {
    ButtonBottomBar(
        modifier = Modifier,
        titleText = Res.string.log_symptoms,
//        leftIcon = R.drawable.ic_back
    )
}