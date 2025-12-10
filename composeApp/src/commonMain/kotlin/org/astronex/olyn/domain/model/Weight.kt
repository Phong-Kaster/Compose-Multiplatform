package org.astronex.olyn.domain.model

import androidx.compose.runtime.Stable
import kotlinx.serialization.Serializable
import org.astronex.olyn.domain.enums.WeightUnit

/**
 * @Stable tells Compose compile that it is immutable, sometime changes. It improve performance when recompose screens
 *
 * The `@Serializable` annotation marks the `BodyTemperature` data class as serializable using Kotlin Serialization.
 * This means instances of this class can be easily converted to &
 * from formats like JSON, making it simple to save, load, or transmit the data.
*/
@Serializable
data class Weight(
    val value: Float = 0F,
    val unit: WeightUnit = WeightUnit.Kilogram
) {
    companion object {
        fun generateFakeWeight(): Weight {
            return Weight(
                value = 50f,
                unit = WeightUnit.Kilogram
            )
        }
    }
}
