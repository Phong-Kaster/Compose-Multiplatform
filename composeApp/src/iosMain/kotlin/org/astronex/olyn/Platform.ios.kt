package org.astronex.olyn

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import okio.Path.Companion.toPath
import org.astronex.olyn.common.Constant
import org.astronex.olyn.common.Constant.SETTING_DATASTORE_NAME
import org.astronex.olyn.data.database.menstruation.MenstruationDatabase
import org.astronex.olyn.data.database.symptom.SymptomDiaryDatabase
import platform.Foundation.NSApplicationSupportDirectory
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSSearchPathForDirectoriesInDomains
import platform.Foundation.NSUserDomainMask
import platform.UIKit.UIApplication
import platform.UIKit.UIDevice
import platform.Foundation.NSURL

class IOSPlatform : Platform {
    override val name: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual fun getPlatformName(): String = "iOS"

/******************************** DATASTORE ********************************/
@OptIn(ExperimentalForeignApi::class)
actual fun createDataStore(): DataStore<Preferences> {
    return PreferenceDataStoreFactory.createWithPath(
        produceFile = {
            val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
                directory = NSDocumentDirectory,
                inDomain = NSUserDomainMask,
                appropriateForURL = null,
                create = false,
                error = null,
            )
            (requireNotNull(documentDirectory).path + "/$SETTING_DATASTORE_NAME").toPath()
        }
    )
}

/******************************** DATABASE ********************************/
actual fun buildMenstruationDatabase(): RoomDatabase.Builder<MenstruationDatabase> {
    val dbFilePath = documentDirectory() + "/${Constant.MENSTRUATION_DATABASE}"
    return Room.databaseBuilder<MenstruationDatabase>(
        name = dbFilePath,
    )
}

actual fun buildSymptomDiaryDatabase(): RoomDatabase.Builder<SymptomDiaryDatabase> {
    val dbFilePath = documentDirectory() + "/${Constant.SYMPTOM_DIARY_DATABASE}"
    return Room.databaseBuilder<SymptomDiaryDatabase>(
        name = dbFilePath,
    )
}

@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory?.path)
}


/********************************
 * open website
 */
actual fun openWebsite(url: String) {
    try {
        val nsUrl = NSURL.URLWithString(url)
        if (nsUrl != null) {
            UIApplication.sharedApplication.openURL(
                nsUrl,
                options = emptyMap<Any?, Any>(),
                completionHandler = null
            )
        }
    } catch (ex: Exception) {
        ex.printStackTrace()
        println("Error opening website: ${ex.message}")
    }
}

actual fun changeLanguage(languageCode: String) {
    println("changeLanguage: $languageCode")
}