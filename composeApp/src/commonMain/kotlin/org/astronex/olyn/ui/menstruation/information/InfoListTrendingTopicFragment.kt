package org.astronex.olyn.ui.menstruation.information

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import compose_multiplatform.composeapp.generated.resources.Res
import compose_multiplatform.composeapp.generated.resources.ic_back
import compose_multiplatform.composeapp.generated.resources.trending_topics
import org.astronex.olyn.domain.enums.InformationItem
import org.astronex.olyn.ui.component.CoreLayout
import org.astronex.olyn.ui.component.CoreTopBar
import org.astronex.olyn.ui.menstruation.information.component.InformationTrendingTopicListLayout
import org.astronex.olyn.ui.theme.customizedTextStyle
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun InfoListTrendingTopicLayout(
    listTrending: List<InformationItem> = listOf(),
    onItemClick: (String) -> Unit = {},
    onBackClick: () -> Unit = {},
) {

    CoreLayout(
        modifier = Modifier.background(Color.White),
        topBar = {
            CoreTopBar(
                title = stringResource(Res.string.trending_topics),
                titleTextStyle = customizedTextStyle(
                    fontSize = 16,
                    fontWeight = 500,
                    color = Color(0xFF111111)
                ),
                leftIcon = Res.drawable.ic_back,
                onClickLeft = onBackClick
            )
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item(key = "InformationCategoryVertical") {
                    InformationTrendingTopicListLayout(
                        listItems = listTrending,
                        onItemClick = onItemClick
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun PreviewInfoListTrendingTopicLayout() {
    InfoListTrendingTopicLayout()
}