package org.astronex.olyn.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.astronex.olyn.domain.enums.Language
import kotlin.code

// commonMain/data/repository/SettingRepository.kt
class SettingDatastore(
    private val dataStore: DataStore<Preferences>,
){
    private val enableOnboardKey = booleanPreferencesKey("enableOnboardKey")
    private val languageCode = stringPreferencesKey("languageKey")

    /************************************
     * Enable Onboard
     */
    val enableOnboardFlow: Flow<Boolean> = dataStore
        .data
        .map { preferences -> preferences[enableOnboardKey] ?: true }

    var enableOnboard: Boolean
        get() = runBlocking { dataStore.data.first()[enableOnboardKey] ?: false }
        set(value) = runBlocking { dataStore.edit { pref -> pref[enableOnboardKey] = value } }

    suspend fun setEnableOnboard(boolean: Boolean) {
        dataStore.edit { pref -> pref[enableOnboardKey] = boolean }
    }

    /************************************
     * Language
     */
    var language: String
        get() = runBlocking { dataStore.data.first()[languageCode] ?: "en" }
        set(value) = runBlocking { dataStore.edit { pref -> pref[languageCode] = value } }
}