package org.astronex.olyn.data.database.menstruation

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.astronex.olyn.data.database.menstruation.dao.MenstruationDao
import org.astronex.olyn.data.database.menstruation.entity.MenstruationDayEntity

// shared/src/commonMain/data/database/menstruation/MenstruationDatabase.kt
@Database(
    entities = [MenstruationDayEntity::class],
    version = 1,
    exportSchema = true,
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class MenstruationDatabase : RoomDatabase() {
    abstract fun menstruationDao(): MenstruationDao
}

/**********************************
 * The Room compiler generates the `actual` implementations.
 * this object  automatically generates Menstruation for both Android and iOS
 **/
@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<MenstruationDatabase> {
    override fun initialize(): MenstruationDatabase
}

/************************************
 * this function is used with Koin inject dependency
 * It returns the Menstruation Database
 */
fun getMenstruationRoomDatabase(
    builder: RoomDatabase.Builder<MenstruationDatabase>
): MenstruationDatabase {
    return builder
        .fallbackToDestructiveMigrationOnDowngrade(dropAllTables = false)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}