package org.astronex.olyn

import androidx.compose.runtime.Composable
import org.astronex.olyn.injection.appModule
import org.astronex.olyn.navigation.ComposeNavGraph
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication


/**
 * - Move the images to the composeApp/src/commonMain/composeResources/drawable directory so that the same flags are available on all platforms:
 */
@Composable
@Preview
fun OlynCommonApplication() {
    KoinApplication(
        application = {
            // The modules() function load the given list of modules
            modules(modules = appModule())
        },
        content = { ComposeNavGraph() },
    )
}