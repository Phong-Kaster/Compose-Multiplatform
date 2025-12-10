package org.astronex.olyn.ui.menstruation.information.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import compose_multiplatform.composeapp.generated.resources.Res
import compose_multiplatform.composeapp.generated.resources.see_all
import org.astronex.olyn.common.Constant.MAX_SIZE
import org.astronex.olyn.common.Constant.MORE_SIZE
import org.astronex.olyn.domain.enums.Category
import org.astronex.olyn.ui.theme.PrimaryColor
import org.astronex.olyn.ui.theme.customizedTextStyle
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun InformationCategoryItem(
    category: Category,
    onItemClick: (String) -> Unit = {},
    onMoreClick: (String) -> Unit = {},
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(category.stringId),
                style = customizedTextStyle(
                    fontSize = 14,
                    fontWeight = 500,
                    color = Color(0xFF974343)
                ),
                maxLines = 1,
            )

            if (category.items.size >= MORE_SIZE) {
            Text(
                modifier = Modifier.clickable {
                    onMoreClick(category.name)
                },
                text = stringResource(Res.string.see_all),
                style = customizedTextStyle(
                    fontSize = 14,
                    fontWeight = 500,
                    color = PrimaryColor
                ),
                textAlign = TextAlign.Center,
                maxLines = 1,
            )
            }
        }

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item { Spacer(Modifier.width(6.dp)) }

            items(category.items.take(MAX_SIZE)) { it ->
                Box(
                    modifier = Modifier
//                        .outerShadow(
//                            shadowRadius = 8.dp,
//                            spreadRadius = 8.dp,
//                            shadowColor = Color.Black.copy(alpha = 0.04f),
//                            shape = RoundedCornerShape(14.dp),
//                        )
                        .height(180.dp)
                        .width(210.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .clickable {
                            onItemClick(it.name)
                        },
                    contentAlignment = Alignment.BottomCenter,
                ) {
                    it.thumbnailId?.let {
                        Image(
                            modifier = Modifier
                                .height(180.dp)
                                .width(210.dp),
                            painter = painterResource(it),
                            contentDescription = "Image",
                            contentScale = ContentScale.FillBounds,
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .background(Color.White)
                            .padding(horizontal = 16.dp),
                    ) {
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(10.dp)
                        )
                        Text(
                            modifier = Modifier,
                            text = stringResource(it.titleId),
                            style = customizedTextStyle(
                                fontSize = 14,
                                fontWeight = 400,
                                color = Color(0xFF111111)
                            ),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 2,
                        )

                    }
                }
            }

            item { Spacer(modifier = Modifier.width(6.dp)) }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    InformationCategoryItem(
        category = Category.CycleDefinition
    )
}
