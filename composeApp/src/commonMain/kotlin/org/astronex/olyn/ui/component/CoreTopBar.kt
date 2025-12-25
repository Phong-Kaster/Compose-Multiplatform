package org.astronex.olyn.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import compose_multiplatform.composeapp.generated.resources.Res
import compose_multiplatform.composeapp.generated.resources.app_name
import compose_multiplatform.composeapp.generated.resources.ic_back
import org.astronex.olyn.ui.theme.customizedTextStyle
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CoreTopBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    titleTextStyle: TextStyle = customizedTextStyle(),
    leftIcon: DrawableResource? = null,
    leftIconTint: Color = Color.Black,
    rightIcon: DrawableResource? = null,
    rightIconTint: Color = Color.Black,
    onClickLeft: () -> Unit = {},
    onClickRight: () -> Unit = {},
    enableClickLeft: Boolean = true,
    enableClickRight: Boolean = true,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Transparent)
            .padding(16.dp)
            .statusBarsPadding(),
    ) {
        if (title != null) {
            Text(
                text = title,
                style = titleTextStyle,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }

        if (leftIcon != null) {
            IconButton(
                enabled = enableClickLeft,
                onClick = {
                    onClickLeft()
                },
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .background(color = Color.Transparent)
                    .size(24.dp)
            ) {
                Icon(
                    painter = painterResource(leftIcon),
                    contentDescription = null,
                    tint = leftIconTint,
                    modifier = Modifier.size(24.dp),
                )
            }
        }


        if (rightIcon != null) {
            IconButton(
                enabled = enableClickRight,
                onClick = {
                    onClickRight()
                },
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .background(color = Color.Transparent)
                    .size(24.dp)
                    .align(Alignment.CenterEnd)
            ) {
                Icon(
                    painter = painterResource(rightIcon),
                    contentDescription = null,
                    tint = rightIconTint,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewBasicTopBarWithBackButton() {
    CoreTopBar(
        title = stringResource(Res.string.app_name),
        leftIcon = Res.drawable.ic_back,
//        rightIcon = R.drawable.ic_launcher_foreground,
        modifier = Modifier.background(color = Color.Gray)
    )
}