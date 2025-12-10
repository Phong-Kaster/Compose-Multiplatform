package org.astronex.olyn.data.database.menstruation.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.astronex.olyn.data.database.menstruation.entity.MenstruationDayEntity

/**
 * crud represents for create, read, update, delete
 *
 * suspend function can not combine with flow
 */
@Dao
interface MenstruationDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg menstruationDayEntity: MenstruationDayEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(menstruationDayEntity: MenstruationDayEntity)

    @Query("SELECT * FROM table_menstruation_day WHERE epochDay = :epochDay")
    suspend fun findByEpochDay(epochDay: Long): MenstruationDayEntity?

    @Query("SELECT * FROM table_menstruation_day")
    suspend fun getAll(): List<MenstruationDayEntity>

    @Query("SELECT * FROM table_menstruation_day")
    fun getAllFlow(): Flow<List<MenstruationDayEntity>>

    @Update
    suspend fun update(menstruationDayEntity: MenstruationDayEntity)

    @Delete
    suspend fun delete(menstruationDayEntity: MenstruationDayEntity)

    @Delete
    suspend fun delete(vararg menstruationDayEntity: MenstruationDayEntity)

    @Query("DELETE FROM table_menstruation_day")
    suspend fun clearAll()

    @Query("SELECT * FROM table_menstruation_day WHERE epochDay BETWEEN :startDate AND :endDate")
    suspend fun findWithDateRate(startDate: Long, endDate: Long): List<MenstruationDayEntity>

    @Query("SELECT * FROM table_menstruation_day WHERE epochDay BETWEEN :startDate AND :endDate")
    fun findWithDateRateFlow(startDate: Long, endDate: Long): Flow<List<MenstruationDayEntity>>
}