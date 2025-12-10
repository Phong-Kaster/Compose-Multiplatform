package org.astronex.olyn.data.database.symptom

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.astronex.olyn.data.database.symptom.converter.SymptomConverter
import org.astronex.olyn.data.database.symptom.dao.SymptomDiaryDao
import org.astronex.olyn.data.database.symptom.entity.SymptomDiaryEntity

// shared/src/commonMain/data/database/symptom/SymptomDiaryDatabase.kt
@Database(
    entities = [SymptomDiaryEntity::class],
    version = 1,
    exportSchema = true,
)
@ConstructedBy(AppDatabaseConstructor::class)
@TypeConverters(SymptomConverter::class)
abstract class SymptomDiaryDatabase : RoomDatabase() {
    abstract fun symptomDiaryDao(): SymptomDiaryDao
}

/**********************************
 * The Room compiler generates the `actual` implementations.
 * this object  automatically generates SymptomDiaryDatabase for both Android and iOS
 **/
@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<SymptomDiaryDatabase> {
    override fun initialize(): SymptomDiaryDatabase
}

/************************************
 * this function is used with Koin inject dependency
 * It returns the SymptomDiaryDatabase
 */
fun getSymptomDiaryDatabase(
    builder: RoomDatabase.Builder<SymptomDiaryDatabase>
): SymptomDiaryDatabase {
    return builder
        .fallbackToDestructiveMigrationOnDowngrade(dropAllTables = false)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}