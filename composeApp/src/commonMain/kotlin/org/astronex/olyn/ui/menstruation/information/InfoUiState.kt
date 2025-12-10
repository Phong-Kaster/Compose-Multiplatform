package org.astronex.olyn.ui.menstruation.information

import androidx.compose.runtime.Stable
import org.astronex.olyn.domain.enums.InformationItem

@Stable
data class InfoUiState(
    val trendingTopics: List<InformationItem> = listOf<InformationItem>(),
){
}