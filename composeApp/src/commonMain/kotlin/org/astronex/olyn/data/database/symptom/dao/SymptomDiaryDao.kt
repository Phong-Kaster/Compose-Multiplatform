package org.astronex.olyn.data.database.symptom.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.astronex.olyn.data.database.symptom.entity.SymptomDiaryEntity

/**
 * suspend function can not combine with flow
 */
@Dao
interface SymptomDiaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(symptomEntity: SymptomDiaryEntity)

    @Update
    suspend fun update(symptomEntity: SymptomDiaryEntity)

    @Delete
    suspend fun delete(symptomEntity: SymptomDiaryEntity)

    @Query("DELETE FROM table_symptom_diary")
    suspend fun deleteAll()

    @Query("SELECT * FROM table_symptom_diary")
    fun getAllFlow(): Flow<List<SymptomDiaryEntity>>

    @Query("SELECT * FROM table_symptom_diary WHERE epochDay = :epochDay")
    suspend fun findByEpochDay(epochDay: Long): SymptomDiaryEntity?

    @Query("SELECT * FROM table_symptom_diary")
    suspend fun getAll(): List<SymptomDiaryEntity>

    @Query("SELECT * FROM table_symptom_diary WHERE epochDay BETWEEN :start AND :end")
    suspend fun findWithDateRange(start: Long, end: Long): List<SymptomDiaryEntity>

    @Query("SELECT * FROM table_symptom_diary WHERE epochDay BETWEEN :start AND :end")
    fun findWithDateRangeFlow(start: Long, end: Long): Flow<List<SymptomDiaryEntity>>

    @Query("SELECT * FROM table_symptom_diary WHERE epochDay=:epochDay LIMIT 1")
    suspend fun getByDay(epochDay: Long): SymptomDiaryEntity?

    @Query("SELECT * FROM table_symptom_diary WHERE epochDay=:epochDay LIMIT 1")
    fun getByDayFlow(epochDay: Long): Flow<SymptomDiaryEntity?>

    @Query("SELECT * FROM table_symptom_diary WHERE epochDay BETWEEN :fromEpochDay AND :toEpochDay ORDER BY epochDay DESC")
    fun getSymptomInFlow(fromEpochDay: Long, toEpochDay: Long): Flow<List<SymptomDiaryEntity>>
}