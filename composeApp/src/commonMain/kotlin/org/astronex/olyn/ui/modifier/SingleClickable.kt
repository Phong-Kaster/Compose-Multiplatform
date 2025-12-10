package org.astronex.olyn.ui.modifier

import androidx.compose.foundation.clickable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import kotlin.time.Clock
import kotlin.time.ExperimentalTime


@OptIn(ExperimentalTime::class)
inline fun Modifier.singleClickable(
    enabled: Boolean = true,
    crossinline onClick: () -> Unit,
    debounceTime: Long = 500L // Default debounce time in milliseconds
): Modifier = composed {
    val lastClickTime = remember { mutableStateOf(0L) }

    if(!enabled) return@composed this

    clickable {
        val currentTime = Clock.System.now().epochSeconds
        if (currentTime - lastClickTime.value > debounceTime) {
            lastClickTime.value = currentTime
            onClick()
        }
    }
}