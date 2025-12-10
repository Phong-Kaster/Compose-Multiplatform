package org.astronex.olyn.ui.menstruation.information

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.astronex.olyn.data.repository.SettingRepository
import org.astronex.olyn.domain.enums.CycleDefinitionItem

class InfoViewModel(
    private val settingRepository: SettingRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(InfoUiState())
    val uiState = _uiState.asStateFlow()

    init {
        generateTrendingTopics()
    }

    fun generateTrendingTopics() {
        viewModelScope.launch(Dispatchers.IO) {
            val trendingTopics = CycleDefinitionItem.generateFakeArticles()
            _uiState.value = _uiState.value.copy(
                trendingTopics = trendingTopics
            )
        }
    }
}