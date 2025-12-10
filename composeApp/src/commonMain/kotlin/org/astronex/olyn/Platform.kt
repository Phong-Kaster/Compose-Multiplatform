package org.astronex.olyn

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.RoomDatabase
import org.astronex.olyn.data.database.menstruation.MenstruationDatabase
import org.astronex.olyn.data.database.symptom.SymptomDiaryDatabase

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun getPlatformName(): String


/******************************** DATASTORE ********************************/
expect fun createDataStore(): DataStore<Preferences>


/******************************** DATABASE ********************************/
expect fun buildMenstruationDatabase(): RoomDatabase.Builder<MenstruationDatabase>
expect fun buildSymptomDiaryDatabase(): RoomDatabase.Builder<SymptomDiaryDatabase>


/********************************
 * openWebsite | changeLanguage which are handle differently for each platform
 */
expect fun openWebsite(url: String)

expect fun changeLanguage(languageCode: String)