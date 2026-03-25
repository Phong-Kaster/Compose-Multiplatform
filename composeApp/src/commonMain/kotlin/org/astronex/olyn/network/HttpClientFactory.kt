package org.astronex.olyn.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.astronex.olyn.core.config.AppConfig

/**
 * Builds a shared [HttpClient] for all targets. Base URL and timeouts come from [AppConfig]; swap
 * values there (or per flavor) when pointing at a real backend.
 */
fun createAppHttpClient(): HttpClient {
    val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        explicitNulls = false
    }
    return HttpClient(getHttpClientEngine()) {
        install(ContentNegotiation) {
            json(json)
        }
        install(HttpTimeout) {
            requestTimeoutMillis = AppConfig.httpRequestTimeoutMs
            connectTimeoutMillis = AppConfig.httpConnectTimeoutMs
            socketTimeoutMillis = AppConfig.httpSocketTimeoutMs
        }
        defaultRequest {
            url(AppConfig.apiBaseUrl)
            contentType(ContentType.Application.Json)
        }
    }
}
