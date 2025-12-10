package org.astronex.olyn.data.datastore


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.astronex.olyn.domain.model.Cycle


// commonMain/data/datastore/UserInfoDatastore.kt
class UserInfoDatastore(
    private val dataStore: DataStore<Preferences>,
) {

    private val birthYearKey = intPreferencesKey("birthYearKey")
    private val averageCycleLengthKey = intPreferencesKey("averageCycleLengthKey")

    private val currentCycleStartEpochDayKey = longPreferencesKey("currentCycleStartEpochDayKey")
    private val nextCycleStartEpochDayKey = longPreferencesKey("nextCycleStartEpochDayKey")


    var birthYear: Int
        get() = runBlocking { dataStore.data.first()[birthYearKey] ?: 0 }
        set(value) {
            runBlocking { dataStore.edit { pref -> pref[birthYearKey] = value } }
        }

    // averageCycleLength is the average length of cycle that users have been provided in onboard flow
    var averageCycleLength: Int
        get() = runBlocking {
            dataStore.data.first()[averageCycleLengthKey] ?: Cycle.TYPICAL_CYCLE_LENGTH
        }
        set(value) {
            runBlocking {
                dataStore.edit { pref ->
                    pref[averageCycleLengthKey] = value
                }
            }
        }


    val averageCycleLengthFlow: Flow<Int> =
        dataStore.data.map { it[averageCycleLengthKey] ?: Cycle.TYPICAL_CYCLE_LENGTH }




    /**
     * currentCycleStartEpochDay represents the start date of the current cycle
     */
    var currentCycleStartEpochDay: Long?
        get() = runBlocking { dataStore.data.first()[currentCycleStartEpochDayKey] }
        set(value) = runBlocking {
            dataStore.edit { pref ->
                if (value != null) {
                    pref[currentCycleStartEpochDayKey] = value
                } else {
                    pref.remove(currentCycleStartEpochDayKey)
                }
            }
        }

    val currentCycleStartEpochDayFlow: Flow<Long?> = dataStore.data.map { pref ->
        pref[currentCycleStartEpochDayKey]
    }


    /**
     * start day of next cycle
     */
    var nextCycleStartEpochDay: Long?
        get() = runBlocking { dataStore.data.first()[nextCycleStartEpochDayKey] }
        set(value) = runBlocking {
            dataStore.edit { pref ->
                if (value != null) {
                    pref[nextCycleStartEpochDayKey] = value
                } else {
                    pref.remove(nextCycleStartEpochDayKey)
                }
            }
        }

    val nextCycleStartEpochDayFlow: Flow<Long?> = dataStore.data.map { pref ->
        pref[nextCycleStartEpochDayKey]
    }
}