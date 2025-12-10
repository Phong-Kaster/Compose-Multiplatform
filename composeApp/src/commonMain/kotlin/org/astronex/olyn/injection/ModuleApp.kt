package org.astronex.olyn.injection


/**
 * Koin modules (network, db, repositories, usecases, viewmodels)
 * A Koin module is the place where we define all our components to be injected
 *
 * single { } - Singleton - Koin container will keep a unique instance of your declared component
 * factory { } - Factory - Koin container will create a new instance each time your component is injected
 * viewModel { } - ViewModel - Koin container will keep a unique instance of your declared component
 * Read more [Definition](https://insert-koin.io/docs/reference/koin-core/definitions) for more keyword
 * */

fun appModule() = listOf(dataStoreModule, databaseModule, repositoryModule, viewModelModule)