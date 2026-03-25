package org.astronex.olyn.data.remote.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.astronex.olyn.common.Resource

/**
 * Runs a suspend block and maps success or failure into [Resource]. Use inside repositories after
 * switching to the IO dispatcher.
 *
 * Example:
 * ```
 * safeApiCall { api.fetchPosts().map { it.toDomain() } }
 * ```
 */
suspend fun <T> safeApiCall(block: suspend () -> T): Resource<T> =
    try {
        Resource.Success(block())
    } catch (e: Throwable) {
        Resource.Error(
            message = e.message ?: e::class.simpleName ?: "Unknown error",
            cause = e,
        )
    }

/**
 * Same as [safeApiCall] but as a cold [Flow]: emits [Resource.Loading] first, then the result.
 * Collect from the UI layer (or ViewModel) if you want stream-style loading states.
 */
fun <T> safeApiCallFlow(block: suspend () -> T): Flow<Resource<T>> = flow {
    emit(Resource.Loading())
    emit(safeApiCall(block))
}
