package org.astronex.olyn.core.config

/**
 * Central place for backend and HTTP tuning. For a real app, override per build flavor or read from
 * platform config instead of hard-coding here.
 *
 * Example: point [apiBaseUrl] at your staging server while developing a new feature.
 */
object AppConfig {
    /** Base URL including trailing slash (Ktor [defaultRequest] appends relative paths). */
    const val apiBaseUrl: String = "https://jsonplaceholder.typicode.com/"

    const val httpRequestTimeoutMs: Long = 30_000L
    const val httpConnectTimeoutMs: Long = 15_000L
    const val httpSocketTimeoutMs: Long = 15_000L
}
