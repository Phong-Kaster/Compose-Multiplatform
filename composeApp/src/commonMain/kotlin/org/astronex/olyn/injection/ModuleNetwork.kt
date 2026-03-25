package org.astronex.olyn.injection

import io.ktor.client.HttpClient
import org.astronex.olyn.data.remote.api.PostApi
import org.astronex.olyn.network.createAppHttpClient
import org.koin.dsl.module

/**
 * Ktor [HttpClient] and typed API entry points ([PostApi] is the sample remote CRUD template).
 */
val networkModule = module {
    single<HttpClient> { createAppHttpClient() }
    single { PostApi(client = get()) }
}
