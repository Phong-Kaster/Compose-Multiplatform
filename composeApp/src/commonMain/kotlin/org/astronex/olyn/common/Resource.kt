package org.astronex.olyn.common

/**
 * Generic wrapper for async work (network, DB, use cases). UI layers typically map [Error] to messages.
 *
 * - [Loading] is useful with [org.astronex.olyn.data.remote.util.safeApiCallFlow]; one-shot suspend calls
 *   often only return [Success] or [Error] from [org.astronex.olyn.data.remote.util.safeApiCall].
 */
sealed class Resource<out T> {
    data class Loading(val message: String? = null) : Resource<Nothing>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val message: String, val cause: Throwable? = null) : Resource<Nothing>()
}
