package org.astronex.olyn.injection

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.astronex.olyn.createDataStore
import org.astronex.olyn.data.datastore.SettingDatastore
import org.astronex.olyn.data.datastore.UserInfoDatastore
import org.koin.dsl.module

val dataStoreModule = module {
    single<DataStore<Preferences>> { createDataStore() }
    single<SettingDatastore> { SettingDatastore(dataStore = get()) }
    single<UserInfoDatastore> { UserInfoDatastore(dataStore = get()) }
}