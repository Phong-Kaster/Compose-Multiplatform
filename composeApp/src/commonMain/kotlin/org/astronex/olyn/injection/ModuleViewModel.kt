package org.astronex.olyn.injection

import org.astronex.olyn.ui.menstruation.home.HomeViewModel
import org.astronex.olyn.ui.menstruation.information.InfoViewModel
import org.astronex.olyn.ui.menstruation.onboard.OnboardViewModel
import org.astronex.olyn.ui.menstruation.setting.SettingViewModel
import org.astronex.olyn.ui.splash.SplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    // Splash View Model
    viewModel { SplashViewModel(settingRepository = get()) }

    // Onboard View Model
    viewModel { OnboardViewModel(settingRepository = get()) }

    // Onboard View Model
    viewModel { HomeViewModel(menstruationRepository = get(), symptomDiaryRepository = get()) }

    // Setting View Model
    viewModel { SettingViewModel(settingRepository = get()) }

    // Information View Model
    viewModel { InfoViewModel(settingRepository = get()) }

}