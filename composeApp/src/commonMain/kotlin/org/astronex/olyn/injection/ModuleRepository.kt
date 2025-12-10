package org.astronex.olyn.injection

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.astronex.olyn.common.Constant
import org.astronex.olyn.data.repository.MenstruationRepository
import org.astronex.olyn.data.repository.SettingRepository
import org.astronex.olyn.data.repository.SymptomDiaryRepository
import org.astronex.olyn.data.repository.UserInfoRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    // Dispatchers which are used to specify which thread pool where the coroutines will run on
    single<CoroutineDispatcher>(qualifier = named(name = Constant.IO)) { Dispatchers.IO }
    single<CoroutineDispatcher>(qualifier = named(name = Constant.DEFAULT)) { Dispatchers.Default }

    // Setting Repository
    single<SettingRepository> {
        SettingRepository(
            ioDispatcher = get(qualifier = named(name = Constant.IO)),
            settingDatastore = get()
        )
    }

    // Menstruation Repository
    single<MenstruationRepository> {
        MenstruationRepository(
            ioDispatcher = get(qualifier = named(name = Constant.IO)),
            menstruationDatabase = get()
        )
    }

    // Menstruation Repository
    single<MenstruationRepository> {
        MenstruationRepository(
            ioDispatcher = get(qualifier = named(name = Constant.IO)),
            menstruationDatabase = get()
        )
    }

    // Symptom Diary Repository
    single<SymptomDiaryRepository> {
        SymptomDiaryRepository(
            ioDispatcher = get(qualifier = named(name = Constant.IO)),
            symptomDiaryDatabase = get()
        )
    }

    // User Info Repository
    single<UserInfoRepository> {
        UserInfoRepository(
            ioDispatcher = get(qualifier = named(name = Constant.IO)),
            userInfoDatastore = get(),
        )
    }
}