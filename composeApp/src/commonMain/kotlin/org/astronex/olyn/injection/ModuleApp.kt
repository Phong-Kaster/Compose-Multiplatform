package org.astronex.olyn.injection


/**
 * Koin modules (network, db, repositories, usecases, viewmodels)
 * A Koin module is the place where we define all our components to be injected
 *
 * single { } - Singleton - Koin container will keep a unique instance of your declared component
 * factory { } - Factory - Koin container will create a new instance each time your component is injected
 * viewModel { } - ViewModel - Koin container will keep a unique instance of your declared component
 * Read more [Definition](https://insert-koin.io/docs/reference/koin-core/definitions) for more keyword
 *
 * **Skeleton template layers**
 * - [networkModule]: Ktor [io.ktor.client.HttpClient], REST facades such as [org.astronex.olyn.data.remote.api.PostApi]
 * - [repositoryModule]: Room/DataStore repos + [org.astronex.olyn.domain.repository.PostRepository] → [org.astronex.olyn.data.repository.impl.PostRepositoryImpl]
 * - [viewModelModule]: UI state; see [org.astronex.olyn.ui.skeleton.SkeletonApiViewModel] for GET/POST/PUT/DELETE wiring
 * - Remote config: [org.astronex.olyn.core.config.AppConfig]; paths: [org.astronex.olyn.data.remote.api.ApiPath]
 * */

fun appModule() = listOf(
    networkModule,
    dataStoreModule,
    databaseModule,
    repositoryModule,
    viewModelModule,
)