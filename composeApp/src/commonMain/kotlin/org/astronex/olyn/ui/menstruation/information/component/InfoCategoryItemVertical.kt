package org.astronex.olyn.ui.menstruation.information.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.astronex.olyn.domain.enums.Category
import org.astronex.olyn.ui.theme.Black90
import org.astronex.olyn.ui.theme.customizedTextStyle
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun InformationCategoryVertical(
    category: Category,
    onItemClick: (String) -> Unit = {},
) {
    Column (
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        category.items.chunked(2).forEach { rowItems ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                rowItems.forEach { item ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
//                            .outerShadow(
//                                shadowRadius = 8.dp,
//                                spreadRadius = 8.dp,
//                                shadowColor = Color.Black.copy(alpha = 0.04f),
//                                shape = RoundedCornerShape(14.dp),
//                            )
                            .height(150.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .clickable {
                                onItemClick(item.name)
                            },
                        contentAlignment = Alignment.BottomCenter,
                    ) {
                        Image(
                            modifier = Modifier
                                .height(180.dp)
                                .width(210.dp),
                            painter = painterResource(item.thumbnailId!!),
                            contentDescription = "Image",
                            contentScale = ContentScale.FillBounds,
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(horizontal = 16.dp)
                        ) {
                            Text(
                                modifier = Modifier,
                                text = stringResource(item.titleId),
                                style = customizedTextStyle(
                                    fontSize = 12,
                                    fontWeight = 600,
                                    color = Black90
                                ),
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 2,
                            )
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(10.dp)
                            )
                        }
                    }
                }

                if (rowItems.size < 2) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Preview
@Composable
private fun InformationCategoryVerticalPreview() {
    InformationCategoryVertical(
        category = Category.CycleDefinition
    )
}