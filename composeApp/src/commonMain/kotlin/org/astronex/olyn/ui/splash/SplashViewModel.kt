package org.astronex.olyn.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.astronex.olyn.data.datastore.SettingDatastore
import org.astronex.olyn.data.repository.SettingRepository

class SplashViewModel(
    private val settingRepository: SettingRepository,
) : ViewModel() {
    private val _enableOnboard = MutableStateFlow(settingRepository.enableOnboard())
    val enableOnboard = _enableOnboard.asStateFlow()

    init {
        collectEnableOnboardFlow()
    }

    private fun collectEnableOnboardFlow() {
        viewModelScope.launch {
            settingRepository.enableOnboardFlow().collectLatest { it ->
                _enableOnboard.value = it
            }
        }
    }
}