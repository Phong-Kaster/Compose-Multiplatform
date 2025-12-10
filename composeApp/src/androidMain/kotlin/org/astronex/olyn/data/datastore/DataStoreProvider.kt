package org.astronex.olyn.data.datastore

import org.astronex.olyn.common.Constant.SETTING_DATASTORE_NAME
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath

object DataStoreProvider {
    @Volatile
    private var INSTANCE: DataStore<Preferences>? = null

    fun getInstance(context: Context): DataStore<Preferences> {
        return INSTANCE ?: synchronized(this) {
            INSTANCE ?: create(context).also {
                INSTANCE = it
            }
        }
    }

    private fun create(context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.createWithPath(
            produceFile = { context.filesDir.resolve(SETTING_DATASTORE_NAME).absolutePath.toPath() }
        )
    }
}