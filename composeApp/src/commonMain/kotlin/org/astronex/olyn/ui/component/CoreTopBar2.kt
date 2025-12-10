package org.astronex.olyn.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import compose_multiplatform.composeapp.generated.resources.ic_home
import org.astronex.olyn.ui.theme.customizedTextStyle
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CoreTopBar2(
    iconLeft: DrawableResource? = null,
    iconRight: DrawableResource? = null,
    title: StringResource = Res.string.app_name,
    titleArrangement: Arrangement.Horizontal = Arrangement.Start,
    onLeftClick: () -> Unit = {},
    onRightClick: () -> Unit = {},
    titleTextStyle: TextStyle = customizedTextStyle()
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .background(color = Color.Transparent)
            .padding(16.dp)
    ) {
        IconButton(
            onClick = {
                onLeftClick()
            },
            modifier = Modifier
                .clip(shape = CircleShape)
                .background(color = Color.Transparent)
                .size(24.dp)
        ) {
            Icon(
                painter = painterResource(resource = iconLeft ?: Res.drawable.ic_back),
                contentDescription = null,
                tint = Color(0xFF2C3141),
                modifier = Modifier.size(24.dp)
            )
        }

        Row(
            horizontalArrangement = titleArrangement,
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .weight(1F)
        ) {
            Text(
                text = stringResource(title),
                maxLines = 1,
                style = titleTextStyle,
                modifier = Modifier
                    .basicMarquee(Int.MAX_VALUE)

            )
        }

        IconButton(
            onClick = { onRightClick() },
            modifier = Modifier
                .clip(shape = CircleShape)
                .background(color = Color.Transparent)
                .size(24.dp)
        ) {
            if (iconRight != null) {
                Icon(
                    painter = painterResource(resource = iconRight),
                    contentDescription = null,
                    tint = Color(0xFF2C3141),
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewCoreTopBar2ArrangementLeft() {
    CoreTopBar2(
        iconLeft = Res.drawable.ic_back,
        title = Res.string.app_name
    )
}

@Preview
@Composable
fun PreviewCoreTopBar2ArrangementCenter() {
    CoreTopBar2(
        iconLeft = Res.drawable.ic_back,
        title = Res.string.app_name,
        titleArrangement = Arrangement.Center,
        iconRight = Res.drawable.ic_back,
    )
}


@Preview
@Composable
fun PreviewCoreTopBar2ArrangementRight() {
    CoreTopBar2(
        iconLeft = Res.drawable.ic_back,
        title = Res.string.app_name,
        titleArrangement = Arrangement.End,
        iconRight = Res.drawable.ic_home,
    )
}