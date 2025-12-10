package org.astronex.olyn.data.repository


import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.astronex.olyn.data.database.menstruation.MenstruationDatabase
import org.astronex.olyn.data.database.menstruation.mapper.MenstruationMapper.toEntity
import org.astronex.olyn.data.database.menstruation.mapper.MenstruationMapper.toModel
import org.astronex.olyn.domain.model.MenstruationDay

/**
 * suspend is a function for a long running operation that can be paused and resumed later
 */
class MenstruationRepository(
    private val ioDispatcher: CoroutineDispatcher,
    private val menstruationDatabase: MenstruationDatabase
) {

    private val menstruationDao = menstruationDatabase.menstruationDao()

    suspend fun insert(menstruationDay: MenstruationDay) {
        withContext(ioDispatcher){
            menstruationDao.insert(menstruationDay.toEntity())
        }
    }

    suspend fun insert(listOfMenstruationDay: List<MenstruationDay>) {
        val listOfMenstruationDayEntity = listOfMenstruationDay.map { it.toEntity() }
        menstruationDao.insert(*listOfMenstruationDayEntity.toTypedArray())
    }

    suspend fun update(menstruationDay: MenstruationDay) {
        menstruationDao.update(menstruationDay.toEntity())
    }

    suspend fun delete(listOfMenstruationDay: List<MenstruationDay>) {
        val listOfMenstruationDayEntity = listOfMenstruationDay.map { it.toEntity() }
        menstruationDao.delete(*listOfMenstruationDayEntity.toTypedArray())
    }

    suspend fun delete(menstruationDay: MenstruationDay) {
        menstruationDao.delete(menstruationDay.toEntity())
    }

    suspend fun findByEpochDay(epochDay: Long): MenstruationDay? {
        return menstruationDao.findByEpochDay(epochDay)?.toModel()
    }

    suspend fun getAll(): List<MenstruationDay> {
        return menstruationDao.getAll().map { it.toModel() }
    }

     fun getAllFlow(): Flow<List<MenstruationDay>> {
        return menstruationDao.getAllFlow().map {
            it.map { it.toModel() }
        }
    }

    suspend fun deleteAllMenstruationDays() {
        menstruationDao.clearAll()
    }

    suspend fun findWithDateRange(startDate: Long, endDate: Long): List<MenstruationDay> {
        return menstruationDao.findWithDateRate(startDate, endDate).map { it.toModel() }
    }


    fun findWithDateRangeFlow(startDate: Long, endDate: Long): Flow<List<MenstruationDay>> {
        return menstruationDao.findWithDateRateFlow(startDate, endDate).map {
            it.map { it.toModel() }
        }
    }

    suspend fun clearAll() {
        menstruationDao.clearAll()
    }
}