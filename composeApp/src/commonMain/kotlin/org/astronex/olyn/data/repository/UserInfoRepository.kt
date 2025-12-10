package org.astronex.olyn.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import org.astronex.olyn.data.datastore.UserInfoDatastore

class UserInfoRepository(
    private val ioDispatcher: CoroutineDispatcher,
    private val userInfoDatastore: UserInfoDatastore,
) {
    suspend fun getBirthYear(): Int {
        return withContext(ioDispatcher) {
            userInfoDatastore.birthYear
        }

    }

    suspend fun setBirthYear(birthYear: Int) {
        return withContext(ioDispatcher) {
            userInfoDatastore.birthYear = birthYear
        }
    }

    // averageCycleLength is the average length of cycle that users have been provided in onboard flow
    suspend fun getAverageCycleLength(): Int {
        return withContext(ioDispatcher) {
            userInfoDatastore.averageCycleLength
        }
    }

    // averageCycleLength is the average length of cycle that users have been provided in onboard flow
     fun getAverageCycleLengthFlow(): Flow<Int> = userInfoDatastore.averageCycleLengthFlow



    suspend fun setAverageCycleLength(averageCycleLength: Int) {
        return withContext(ioDispatcher) {
            userInfoDatastore.averageCycleLength = averageCycleLength
        }
    }

    // currentCycleStartEpochDay represents the start date of the current cycle
    suspend fun getCurrentCycleStartEpochDay(): Long? {
        return withContext(ioDispatcher) {
            userInfoDatastore.currentCycleStartEpochDay
        }
    }

    /**
     * currentCycleStartEpochDay represents the start date of the current cycle
     */
    suspend fun getCurrentCycleStartEpochDayFlow(): Flow<Long?> {
        return withContext(ioDispatcher) {
            userInfoDatastore.currentCycleStartEpochDayFlow
        }
    }

    suspend fun setCurrentCycleStartEpochDay(epochDay: Long?) {
        return withContext(ioDispatcher) {
            userInfoDatastore.currentCycleStartEpochDay = epochDay
        }
    }


    /**
     * start day of next cycle
     */
    suspend fun getNextCycleStartEpochDayFlow(): Flow<Long?> {
        return withContext(ioDispatcher) {
            userInfoDatastore.nextCycleStartEpochDayFlow
        }
    }

    suspend fun getNextCycleStartEpochDay(): Long? {
        return withContext(ioDispatcher) {
            userInfoDatastore.nextCycleStartEpochDay
        }
    }

    suspend fun setNextCycleStartEpochDay(epochDay: Long?) {
        return withContext(ioDispatcher) {
            userInfoDatastore.nextCycleStartEpochDay = epochDay
        }
    }
}