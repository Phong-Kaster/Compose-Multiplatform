package org.astronex.olyn.ui.menstruation.setting.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.layout.Arrangement
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
import compose_multiplatform.composeapp.generated.resources.disclaimer
import compose_multiplatform.composeapp.generated.resources.ic_arrow_right
import compose_multiplatform.composeapp.generated.resources.ic_disclaimer_setting
import org.astronex.olyn.ui.theme.PrimaryColor
import org.astronex.olyn.ui.theme.customizedTextStyle
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SettingItem(
    modifier: Modifier = Modifier,
    nameId: StringResource,
    iconId: DrawableResource,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(vertical = 20.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp),
    ) {

        Icon(
            modifier = Modifier.size(28.dp),
            painter = painterResource(iconId),
            contentDescription = "icon",
            tint = PrimaryColor,
        )

        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(nameId),
            style = customizedTextStyle(16, 500, color = Color.Black),
        )

        Icon(
            modifier = Modifier.size(14.dp),
            painter = painterResource(Res.drawable.ic_arrow_right),
            contentDescription = "icon",
            tint = Color(0xFF888888),
        )
    }
}

@Preview
@Composable
private fun PreviewSettingItem() {
    SettingItem(
        modifier = Modifier.padding(16.dp),
        nameId = Res.string.disclaimer,
        iconId = Res.drawable.ic_disclaimer_setting,
        onClick = {}
    )
}