package org.astronex.olyn.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import org.astronex.olyn.data.datastore.SettingDatastore

class SettingRepository(
    private val ioDispatcher: CoroutineDispatcher,
    private val settingDatastore: SettingDatastore,
) {
    /************************************
     * Enable Onboard
     */
    fun enableOnboard(): Boolean {
        return settingDatastore.enableOnboard
    }

    suspend fun setEnableOnboard(boolean: Boolean) {
        return withContext(context = ioDispatcher) {
            settingDatastore.enableOnboard = boolean
        }
    }

     fun enableOnboardFlow(): Flow<Boolean> {
        return settingDatastore.enableOnboardFlow
    }

    /************************************
     * Language
     */

}