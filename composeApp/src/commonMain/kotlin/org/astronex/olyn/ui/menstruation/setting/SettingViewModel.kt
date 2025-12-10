package org.astronex.olyn.ui.menstruation.setting

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.astronex.olyn.data.datastore.SettingDatastore
import org.astronex.olyn.data.repository.SettingRepository
import org.astronex.olyn.domain.enums.Language

class SettingViewModel(
    private val settingRepository: SettingRepository,
): ViewModel() {

    private val _uiState = MutableStateFlow(SettingUiState())
    val uiState: StateFlow<SettingUiState> = _uiState.asStateFlow()


    fun changeLanguage(language: Language) {
        _uiState.value = _uiState.value.copy(language = language)

    }
}