package org.astronex.olyn.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.astronex.olyn.data.database.symptom.SymptomDiaryDatabase
import org.astronex.olyn.data.database.symptom.mapper.SymptomDiaryMapper.toEntity
import org.astronex.olyn.data.database.symptom.mapper.SymptomDiaryMapper.toModel
import org.astronex.olyn.domain.model.SymptomDiary

class SymptomDiaryRepository(
    private val ioDispatcher: CoroutineDispatcher,
    private val symptomDiaryDatabase: SymptomDiaryDatabase,
) {
    private val dao = symptomDiaryDatabase.symptomDiaryDao()

    // Define repository methods here to interact with symptomDiaryDao
    suspend fun insert(symptomDiary: SymptomDiary) {
        withContext(ioDispatcher) {
            dao.insert(symptomDiary.toEntity())
        }
    }

    suspend fun update(symptomDiary: SymptomDiary) {
        withContext(ioDispatcher) {
            dao.update(symptomDiary.toEntity())
        }
    }

    suspend fun delete(symptomDiary: SymptomDiary) {
        withContext(ioDispatcher) {
            dao.delete(symptomDiary.toEntity())
        }
    }

    suspend fun clearAll() {
        withContext(ioDispatcher) { dao.deleteAll() }
    }

    suspend fun getAll(): List<SymptomDiary> {
        return withContext(ioDispatcher) {
            dao.getAll().map { it.toModel() }
        }
    }

    suspend fun findByEpochDay(epochDay: Long): SymptomDiary? {
        return withContext(ioDispatcher) {
            dao.findByEpochDay(epochDay)?.toModel()
        }
    }


    suspend fun findWithDateRange(startEpochDay: Long, endEpochDay: Long): List<SymptomDiary> {
        return withContext(ioDispatcher) {
            dao.findWithDateRange(startEpochDay, endEpochDay).map { it.toModel() }
        }
    }

    suspend fun findWithDateRangeFlow(
        startEpochDay: Long,
        endEpochDay: Long
    ): Flow<List<SymptomDiary>> {
        return dao.findWithDateRangeFlow(startEpochDay, endEpochDay)
            .map { list -> list.map { it.toModel() } }
    }

    suspend fun getSymptomNoteByDay(epochDay: Long): SymptomDiary? {
        return withContext(ioDispatcher)  {
            dao.getByDay(epochDay)?.toModel()
        }
    }
}