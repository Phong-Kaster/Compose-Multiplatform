package org.astronex.olyn

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Build
import androidx.core.net.toUri
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import androidx.room.RoomDatabase
import org.astronex.olyn.common.Constant
import org.astronex.olyn.data.database.menstruation.MenstruationDatabase
import org.astronex.olyn.data.database.symptom.SymptomDiaryDatabase
import org.astronex.olyn.data.datastore.DataStoreProvider


class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun getPlatformName(): String = "Android"


/********************************
 * Application Context
 */
lateinit var applicationContext: Context
fun initAndroidApplicationContext(context: Context) {
    applicationContext = context.applicationContext
}

/********************************
 * Data Store
 */
actual fun createDataStore(): DataStore<Preferences> {
    return DataStoreProvider.getInstance(context = applicationContext)
}


/******************************** DATABASE ********************************/
actual fun buildMenstruationDatabase(): RoomDatabase.Builder<MenstruationDatabase> {
    val dbFile = applicationContext.getDatabasePath(Constant.MENSTRUATION_DATABASE)
    return Room.databaseBuilder<MenstruationDatabase>(
        context = applicationContext,
        name = dbFile.absolutePath
    )
}

actual fun buildSymptomDiaryDatabase(): RoomDatabase.Builder<SymptomDiaryDatabase> {
    val dbFile = applicationContext.getDatabasePath(Constant.SYMPTOM_DIARY_DATABASE)
    return Room.databaseBuilder<SymptomDiaryDatabase>(
        context = applicationContext,
        name = dbFile.absolutePath
    )
}

/********************************
 * open website
 */
actual fun openWebsite(url: String) {
    try {
        val uri = url.toUri()
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = uri
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        applicationContext.startActivity(intent)
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
}

actual fun changeLanguage(languageCode: String) {
    // todo: hay viet cai ham ma doi ngon ngu theo languageCode
    val res: Resources = applicationContext.resources
}