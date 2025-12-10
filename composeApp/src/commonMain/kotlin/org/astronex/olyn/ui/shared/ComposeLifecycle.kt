package org.astronex.olyn.ui.shared

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import org.astronex.olyn.util.platform.logcat

/**
 * Compose Lifecycle provides lifecycle state of composable
 */
@Composable
fun ComposeLifecycle(
    screenName: String = "",
    onCreate: () -> Unit = {},
    onStart: () -> Unit = {},
    onResume: () -> Unit = {},
    onPause: () -> Unit = {},
    onStop: () -> Unit = {},
    onDestroy: () -> Unit = {}
) {
    /************************************
     * Collect lifecycle state with flows
     */
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(
        key1 = lifecycleOwner.lifecycle,
        effect = {
            val observer = LifecycleEventObserver { _, event ->
                logcat(tag = "Lifecycle $screenName", message = "$event", enableHorizontalLine = false)
                when (event) {
                    Lifecycle.Event.ON_CREATE -> {
                        // Handle ON_CREATE event
                        onCreate()
                    }

                    Lifecycle.Event.ON_START -> {
                        // Handle ON_START event
                        onStart()
                    }

                    Lifecycle.Event.ON_RESUME -> {
                        // Handle ON_RESUME event
                        onResume()
                    }

                    Lifecycle.Event.ON_PAUSE -> {
                        // Handle ON_PAUSE event
                        onPause()
                    }

                    Lifecycle.Event.ON_STOP -> {
                        // Handle ON_STOP event
                        onStop()
                    }

                    Lifecycle.Event.ON_DESTROY -> {
                        // Handle ON_DESTROY event
                        onDestroy()
                    }
                    else -> {}
                }
            }
            lifecycleOwner.lifecycle.addObserver(observer)

            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }
    )
}