package org.astronex.olyn.ui.menstruation.information

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.astronex.olyn.ui.menstruation.information.component.InformationCategoryItem
import compose_multiplatform.composeapp.generated.resources.Res
import compose_multiplatform.composeapp.generated.resources.articles
import compose_multiplatform.composeapp.generated.resources.trending_topics
import org.astronex.olyn.domain.enums.Category
import org.astronex.olyn.ui.core.CoreLayout
import org.astronex.olyn.ui.core.CoreTopBar
import org.astronex.olyn.ui.menstruation.home.component.HomeTrendingArticle
import org.astronex.olyn.ui.theme.customizedTextStyle
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun InformationLayout(
    uiState: InfoUiState = InfoUiState(),
    onOpenArticle: (String) -> Unit = {},
    onSeeFullClick: (String) -> Unit = {},
    onSeeTrendingListClick: () -> Unit = {}
) {
    CoreLayout(
        modifier = Modifier.background(Color.White),
        topBar = {
            CoreTopBar(
                title = stringResource(Res.string.articles),
                titleTextStyle = customizedTextStyle(
                    fontSize = 16,
                    fontWeight = 500,
                    color = Color(0xFF111111)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            )
        },
        content = {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                item(key = "TrendingTopics") {
                    HomeTrendingArticle(
                        title = stringResource(Res.string.trending_topics),
                        listArticles = uiState.trendingTopics,
                        onReadArticle = {
                            onOpenArticle(it.name)
                        },
                        modifier = Modifier.background(Color(0xFFFFF3F2)),
                        onMoreClick = onSeeTrendingListClick
                    )
                }

                item(key = "InformationCategory") {
                    Category.entries.forEach {
                        Spacer(modifier = Modifier.height(24.dp))

                        if (it.displayedArticle) {
                            InformationCategoryItem(
                                category = it,
                                onItemClick = onOpenArticle,
                                onMoreClick = onSeeFullClick
                            )
                        }
                    }
                }

                item(key = "Spacer") { Spacer(modifier = Modifier.height(40.dp)) }
            }
        }
    )
}

@Preview
@Composable
private fun PreviewInformationLayout() {
    InformationLayout()
}