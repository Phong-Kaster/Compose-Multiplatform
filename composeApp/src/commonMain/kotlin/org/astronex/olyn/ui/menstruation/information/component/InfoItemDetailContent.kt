package org.astronex.olyn.ui.menstruation.information.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import compose_multiplatform.composeapp.generated.resources.Res
import compose_multiplatform.composeapp.generated.resources.content_of_disclaimer
import compose_multiplatform.composeapp.generated.resources.disclaimer
import compose_multiplatform.composeapp.generated.resources.dont_miss_this
import compose_multiplatform.composeapp.generated.resources.ic_disclaimer
import compose_multiplatform.composeapp.generated.resources.source
import org.astronex.olyn.domain.enums.CycleDefinitionItem
import org.astronex.olyn.domain.enums.InformationItem
import org.astronex.olyn.ui.menstruation.home.component.HomeTrendingArticle
import org.astronex.olyn.ui.theme.Black90
import org.astronex.olyn.ui.theme.PrimaryColor
import org.astronex.olyn.ui.theme.StyledText
import org.astronex.olyn.ui.theme.customizedTextStyle
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun InformationItemDetailContent(
    modifier: Modifier = Modifier,
    item: InformationItem,
//    context: Context,
    onItemClick: (InformationItem) -> Unit = {},
    onMoreClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(item.titleId),
            style = customizedTextStyle(
                fontSize = 16,
                fontWeight = 700,
                color = Color(0xFF111111)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        )

        item.imageDetailId?.let {
            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(it),
                contentDescription = "Image",
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.FillWidth,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        StyledText(
            modifier = Modifier.padding(horizontal = 16.dp),
            htmlText = stringResource(item.contentId),
        )

        Spacer(modifier = Modifier.height(8.dp))

        item.linkSourceId?.let { link ->
            Row(
                Modifier.padding(horizontal = 16.dp),
            ) {
                Text(
                    text = stringResource(Res.string.source),
                    style = customizedTextStyle(
                        fontSize = 14,
                        fontWeight = 700,
                        color = Color(0xFF111111)
                    ),
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .clickable {
//                            UrlUtil.openUrl(
//                                context = context,
//                                url = context.getString(link)
//                            )
                        },
                    text = if (item.sourceNameId != null) {
                        stringResource(item.sourceNameId!!)
                    } else {
                        stringResource(link)
                    },
                    style = customizedTextStyle(
                        fontSize = 16,
                        fontWeight = 500,
                        color = Color.Blue.copy(0.8f)
                    ).copy(textDecoration = TextDecoration.Underline),
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        HomeTrendingArticle(
            title = stringResource(Res.string.dont_miss_this),
            listArticles = CycleDefinitionItem.generateFakeArticles(),
            onReadArticle = onItemClick,
            modifier = Modifier
                .background(Color(0xFFFFF3F2)),
            onMoreClick = {
                onMoreClick()
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_disclaimer),
                contentDescription = null,
                tint = PrimaryColor,
                modifier = Modifier.size(18.dp)
            )

            Text(
                text = stringResource(Res.string.disclaimer).replaceFirstChar { it.uppercase() },
                style = customizedTextStyle(
                    fontSize = 16,
                    fontWeight = 500,
                    color = Color(0xFF111111)
                )
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = stringResource(Res.string.content_of_disclaimer),
            style = customizedTextStyle(
                fontSize = 12,
                fontWeight = 400,
                color = Black90
            ),
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(38.dp))
    }
}

@Preview
@Composable
private fun InformationItemDetailContentPreview() {
    InformationItemDetailContent(
        item = CycleDefinitionItem.CycleDefinition1,
        modifier = Modifier.background(Color.White)
    )
}