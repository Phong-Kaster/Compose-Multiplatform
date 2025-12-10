package org.astronex.olyn.ui.menstruation.onboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.astronex.olyn.data.repository.SettingRepository

class OnboardViewModel(
    private val settingRepository: SettingRepository
) : ViewModel() {

    fun setEnableOnboard(boolean: Boolean) {
        viewModelScope.launch {
            settingRepository.setEnableOnboard(boolean)
        }
    }
}