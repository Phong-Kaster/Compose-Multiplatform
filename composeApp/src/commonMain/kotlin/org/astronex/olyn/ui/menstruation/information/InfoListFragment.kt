package org.astronex.olyn.ui.menstruation.information

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.astronex.olyn.ui.menstruation.information.component.InformationCategoryVertical
import compose_multiplatform.composeapp.generated.resources.Res
import compose_multiplatform.composeapp.generated.resources.ic_back
import org.astronex.olyn.domain.enums.Category
import org.astronex.olyn.ui.core.CoreLayout
import org.astronex.olyn.ui.core.CoreTopBar
import org.astronex.olyn.ui.theme.customizedTextStyle
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.text.category

@Composable
fun InformationItemListScreen(
    categoryId: String? = null,
    onItemClick: (String) -> Unit = {},
    onBackClick: () -> Unit = {},
) {
    var category: Category? by mutableStateOf(null)
    category = Category.entries.find { it.name == categoryId }

    category?.let {
        InformationItemListLayout(
            category = it,
            onItemClick = onItemClick,
            onBackClick = onBackClick
        )
    } ?: onBackClick
}

@Composable
private fun InformationItemListLayout(
    category: Category,
    onBackClick: () -> Unit = {},
    onItemClick: (String) -> Unit = {}
) {
    CoreLayout(
        modifier = Modifier.background(Color.White),
        topBar = {
            CoreTopBar(
                title = stringResource(category.stringId),
                titleTextStyle = customizedTextStyle(
                    fontSize = 16,
                    fontWeight = 500,
                    color = Color(0xFF111111)
                ),
                leftIcon = Res.drawable.ic_back,
                onClickLeft = onBackClick
            )
        },
        bottomBar = {
//            CoreBannerAdContent(bannerAdGroup = AdsProvider.bannerAll)
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item(key = "InformationCategoryVertical") {
                    InformationCategoryVertical(
                        category = category,
                        onItemClick = onItemClick
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun InformationSeeFullPreview() {
    InformationItemListScreen()
}