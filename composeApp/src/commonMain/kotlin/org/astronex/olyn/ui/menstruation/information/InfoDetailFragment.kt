package org.astronex.olyn.ui.menstruation.information

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import compose_multiplatform.composeapp.generated.resources.Res
import compose_multiplatform.composeapp.generated.resources.ic_back
import org.astronex.olyn.domain.enums.Category
import org.astronex.olyn.ui.menstruation.information.component.InformationItemDetailContent
import org.astronex.olyn.domain.enums.CycleDefinitionItem
import org.astronex.olyn.domain.enums.InformationItem
import org.astronex.olyn.ui.component.CoreTopBar2
import org.astronex.olyn.ui.component.CoreLayout
import org.astronex.olyn.ui.theme.customizedTextStyle
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun InformationItemDetailScreen(
    infoId: String?,
    onBackClick: () -> Unit = {},
) {
    var item: InformationItem? by mutableStateOf(null)


        Category.entries.forEach { it ->
            it.items.find { it.name == infoId }?.let { item = it }
        }


    item?.let {
        InformationItemDetailLayout(
            item = it,
            onBackClick = onBackClick
        )
    } ?: onBackClick
}

@Composable
private fun InformationItemDetailLayout(
    item: InformationItem,
    onItemClick: (String) -> Unit = {},
    onBackClick: () -> Unit = {},
    onMoreClick: () -> Unit = {}
) {
    CoreLayout(
        modifier = Modifier.background(Color.White),
        topBar = {
            CoreTopBar2(
                title = item.titleId,
                titleTextStyle = customizedTextStyle(
                    fontSize = 16,
                    fontWeight = 500,
                    color = Color(0xFF272727)
                ),
                iconLeft = Res.drawable.ic_back,
                onLeftClick = onBackClick
            )
        },

        content = {
            InformationItemDetailContent(
                item = item,
                onItemClick = {
                    onItemClick(it.name)
                },
                onMoreClick = onMoreClick
            )
        }
    )
}

@Preview
@Composable
private fun InformationItemDetailScreenPreview() {
    InformationItemDetailLayout(
        item = CycleDefinitionItem.CycleDefinition1,
    )
}
